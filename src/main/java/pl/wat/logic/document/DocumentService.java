package pl.wat.logic.document;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.insert.TableCellInsert;
import pl.jsolve.templ4docx.insert.TableRowInsert;
import pl.jsolve.templ4docx.variable.TableVariable;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variable;
import pl.jsolve.templ4docx.variable.Variables;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.logic.user.account.UserAccountService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    LectureRepository lectureRepository;

    List<String> documentList;

    public DocumentService() {
        documentList = new ArrayList<String>();
        documentList.add("Wójt");   //0
        documentList.add("Burmistrz");      //1
        documentList.add("Prezydent Miasta");       //2
        documentList.add("Komendant Powiatowy Państwowej Straży Pożarnej"); //3
        documentList.add("Komendant Miejski Państwowej Straży Pożarnej");//4
        documentList.add("Komendant Powiatowy Policji");//5
        documentList.add("Komendant Rejonowy Policji");//6
        documentList.add("Komendant Miejski Policji");//7
        documentList.add("Kierownik Jednostki Organizacyjnej Pomocy Doraźnej");//8
        documentList.add("Państwowy Inspektor Sanitarny");//9
        documentList.add("Dysponent Zespołów Ratownictwa Medycznego");//10
    }

    public ResponseEntity<byte[]> createDocumentResponse(XWPFDocument document){
        //Przygotowanie formy bajtowej pliku
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            document.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] contents = baos.toByteArray();
        //Konfiguracja odpowiedzi
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        String filename = "dokument.docx"; //dla api ogolnego. W angularze nadajemy nowa nazwe
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        //Przygotowanie odpowiedzi
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return responseEntity;
    }

    public XWPFDocument getDocument(int idEvent, int nrDocument){

         Event event = eventRepository.getOne(idEvent);
        if(event!=null && nrDocument>=0 && nrDocument<=10) {
            //Wczytac szablon
            Docx docx = new Docx("src\\main\\resources\\documents\\test.docx");
            Docx document = new Docx("src\\main\\resources\\documents\\Wniosek_o_opinie.docx");

            Date date = new Date(System.currentTimeMillis());
            //Przygotowac zmienne
            Variables variables = new Variables();
            variables.addTextVariable(new TextVariable("${data}", date.toString()));
            variables.addTextVariable(new TextVariable("${miejsce}", event.getPlace().getCity().getCityName()));
            variables.addTextVariable(new TextVariable("${AdresatWniosku}", documentList.get(nrDocument)));
            variables.addTextVariable(new TextVariable("${imieO}", event.getUser().getFirstname()));
            variables.addTextVariable(new TextVariable("${nazwiskoO}", event.getUser().getLastname()));
            variables.addTextVariable(new TextVariable("${emailO}", event.getUser().getEmail()));
            variables.addTextVariable(new TextVariable("${dtPI}", event.getStartTime().toString().substring(0, 16)));
            variables.addTextVariable(new TextVariable("${mscPI}", event.getPlace().getCity().getCityName()));


            if (event.getUser().getPhone() == null) //przyklad problem braku wartosci w bazie
                variables.addTextVariable(new TextVariable("${telefonO}", "........................................."));
            else
                variables.addTextVariable(new TextVariable("${telefonO}", event.getUser().getPhone()));

            variables.addTextVariable(new TextVariable("${limitmiejscPI}", String.valueOf(event.getCapacity())));


            //tabela
            TableVariable tableVariable = new TableVariable();
            List<Variable> kol1Variables = new ArrayList<Variable>();
            List<Variable> kol2Variables = new ArrayList<Variable>();
            List<Variable> kol3Variables = new ArrayList<Variable>();

            List<Lecture> allByEventOrderByStartTime = lectureRepository.getAllByEventOrderByStartTime(event);
            for (Lecture lecture : allByEventOrderByStartTime) {
                kol1Variables.add(new TextVariable("${PRdtRozpoczecia}", lecture.getStartTime().toString().substring(0, 16)));
                kol2Variables.add(new TextVariable("${PRdtZakonczenia}", lecture.getEndTime().toString().substring(0, 16)));
                kol3Variables.add(new TextVariable("${PRNazwa}", lecture.getLectureName()));
            }

            tableVariable.addVariable(kol1Variables);
            tableVariable.addVariable(kol2Variables);
            tableVariable.addVariable(kol3Variables);
            variables.addTableVariable(tableVariable);

            //end: tabela


            //Wypelnic szablon zmiennymi
            document.fillTemplate(variables);

            //Można ewentualnie zapisac do pliku
            //docx.save("D:\\Projekty\\LocalRepoEventer\\eventer\\src\\main\\resources\\documents\\testEdit.docx");

            //Zwrocic dokument .docx (XWPF) w celu pozniejszego streamowania do usera jako jego download
            return document.getXWPFDocument();
        }
        return null;
    }

    public List<String> getDocumentList() {
        return documentList;
    }
}
