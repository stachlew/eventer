package pl.wat.logic.document;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;
import pl.wat.db.domain.user.User;
import pl.wat.logic.user.account.UserAccountService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class DocumentService {

    @Autowired
    UserAccountService userAccountService;

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

    public XWPFDocument getDocument(String username){
        User user = userAccountService.getUser(username);

        //Wczytac szablon
        Docx docx = new Docx("src\\main\\resources\\documents\\test.docx");

        //Przygotowac zmienne
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("${docType}", "Nota do sanepidu"));
        variables.addTextVariable(new TextVariable("${name}", user.getFirstname()));
        variables.addTextVariable(new TextVariable("${lastname}", user.getLastname()));
        variables.addTextVariable(new TextVariable("${username}", user.getUsername()));
        variables.addTextVariable(new TextVariable("${email}", user.getEmail()));

        if(user.getPhone()==null) //przyklad problem braku wartosci w bazie
            variables.addTextVariable(new TextVariable("${phone}", ""));
        else
            variables.addTextVariable(new TextVariable("${phone}", user.getPhone()));

        variables.addTextVariable(new TextVariable("${parTitle}", "EVENTER"));
        variables.addTextVariable(new TextVariable("${par0}", "WZIĄć"));
        variables.addTextVariable(new TextVariable("${par1}", "Parametr1"));
        variables.addTextVariable(new TextVariable("${par2}", "Parek2"));
        variables.addTextVariable(new TextVariable("${par3}", "PARAMETR3"));
        variables.addTextVariable(new TextVariable("${par4}", "Partens4"));
        variables.addTextVariable(new TextVariable("${par5}", "Parametr duży 5, słownie piąty."));
        variables.addTextVariable(new TextVariable("${par6}", "Stopka dokumentu żółćą pełna"));

        //Wypelnic szablon zmiennymi
        docx.fillTemplate(variables);

        //Można ewentualnie zapisac do pliku
        //docx.save("D:\\Projekty\\LocalRepoEventer\\eventer\\src\\main\\resources\\documents\\testEdit.docx");

        //Zwrocic dokument .docx (XWPF) w celu pozniejszego streamowania do usera jako jego download
        return docx.getXWPFDocument();
    }

}
