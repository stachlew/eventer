package pl.wat.api.demo;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jsolve.sweetener.criteria.restriction.In;
import pl.wat.db.domain.Customer;
import pl.wat.db.domain.DemoClass;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.EventSearchRepository;
import pl.wat.db.repository.event.OpinionRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.db.repository.event.location.CityRepository;
import pl.wat.db.repository.event.location.RegionRepository;
import pl.wat.logic.demo.CustomerService;
import pl.wat.logic.document.DocumentService;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;

import pl.wat.logic.event.dashboard.EventDashboardLecturesService;
import pl.wat.logic.event.dashboard.EventDashboardParticipantsService;

import pl.wat.logic.event.image.EventImageService;
import pl.wat.logic.event.search.EventSearchService;
import pl.wat.logic.event.view.EventRegisterService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user._model.UserRegisterForm;
import pl.wat.logic.user.account.UserAccountService;
import pl.wat.logic.user.register.UserRegisterService;
import pl.wat.logic.util.StatisticService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//KONTROLER DEMO typu Ctrl+c, Ctrl+v dla dalszych metod kontrolerow w projekcie
@RestController
@RequestMapping("/api")
public class DemoRestController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    DocumentService documentService;

    @Autowired
    EventImageService eventImageService;

    @Autowired
    SpeakerRepository speakerRepository;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventRegisterService eventRegisterService;



    @Autowired
    EventDashboardParticipantsService eventDashboardParticipantsService;

    @Autowired
    EventDashboardLecturesService eventDashboardLecturesService;

    @Autowired
    EventSearchRepository eventSearchRepository;
    @Autowired
    EventSearchService eventSearchService;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CityRepository cityRepository;

    @Autowired
    OpinionRepository opinionRepository;

    @Autowired
    UserRegisterService userRegisterService;

    @Autowired
    StatisticService statisticService;


    //METODA DO SZYBKIEGO TESTOWANIA SERWISOW [ wchodzic na: http://localhost:8080/api/test ]
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody String getTest(){
        System.out.println("URUCHOMIENIE TESTU");
        boolean status1=false;
        boolean status2=false;

        System.out.println(statisticService.getCountEvent());
        System.out.println(statisticService.getCountParticipant());
        System.out.println(statisticService.getCountVisits());
        System.out.println(statisticService.getAvgParticipant());
        System.out.println(statisticService.getAvgVisits());
        // userRegisterService.createUser(new UserRegisterForm("kamil123","Kam","Kam","kamil.kosiorek.94@gmail.com","3232322"));
        //System.out.println(opinionRepository.countByEventIdEventAndRate(1,1));
       // eventRepository.incrementVisit(1);
       // eventSearchForm.setCity(cityRepository.findOne(1));
        //eventSearchForm.setTextContent("pol");
//        eventSearchForm.setFromGeoLenght("19");
//        eventSearchForm.setToGeoLenght("52");
//        eventSearchForm.setFromGeoWidth("49");
//        eventSearchForm.setToGeoWidth("53");
//       List<EventSearchResult> eventSearchResults = eventSearchService.findEvents(eventSearchForm);
//        for (EventSearchResult e: eventSearchResults
//             ) {
//            System.out.println(e.getTitle());
//        }


        //Deklaracja
        //List<Integer> distinctSpeakersByIdEvent = speakerRepository.getDistinctIdSpeakersByIdEvent(1);

//        for (Integer s: distinctSpeakersByIdEvent
//             ) {
//            System.out.println(s);
//
//        }
//        ParticipantForm participantForm = new ParticipantForm("adam","wan","da@da2",1100);
//        eventRegisterService.registeredParticipant(participantForm);


        //User newUser = new User("test","1234","Adam","Nowak","nowak@wp.pl","425754243");
        //User newAdmin = new User("test","1234","Admin","Nowak","nowak@wp.pl","425754243");

        //Wykonywanie
        // status1 = userRegisterService.createUser(newUser);
        //status2 = userAccountService.deleteUser("user");

        //Zwrot wyniku
        if(status1 && status2)
            return "OK!";
        else
            return "FAIL";
    }


    //Przyklad pobierania dokumentu
    //@PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/download/{nr}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getDocument(Authentication authentication,@PathVariable String nr){
       // if(authentication!=null){
            //Dla kogo tworzony
           // String username = authentication.getName();

            //Pobranie odpowiednio przygotowanego w serwisie dokumentu
            XWPFDocument document = documentService.getDocument(1,Integer.valueOf(nr));

            //Zwrocenie przygotowanej odpowiedzi
            return documentService.createDocumentResponse(document);
        //}
       // return null;
    }


    //POBRANIE DANYCH BEZ AUTORYZACJI
    @RequestMapping(value = "/getGuest",method = RequestMethod.GET)
    @ResponseBody Iterable<Customer> getGuest(){
        System.out.println("METODA DOSTEPNA DLA GOSCIA");
        Iterable<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    //WYSLANIE ZADANIA BEZ ODPOWIEDZI Z AUTORYZACJA [USER]
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(value = HttpStatus.OK)
    public void getUser(Authentication auth){
        System.out.println("METODA DOSTEPNA DLA USERA");
        System.out.println(auth.getName()+" "+auth.getAuthorities());
    }

    //POBRANIE DANYCH Z AUTORYZACJA [ADMIN]
    @RequestMapping(value = "/getAdmin",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody Iterable<Customer> getAdmin(){
        System.out.println("METODA DOSTEPNA DLA  ADMINA");
        customerService.testService();
        return customerService.getAllCustomers();
    }

    //WYSLANIE DANYCH BEZ AUTORYZACJI
    @RequestMapping(value = "/postGuest",method = RequestMethod.POST)
    public void postGuest(@RequestBody DemoClass demoClass){
        System.out.println("METODA POST DOSTEPNA DLA GOSCIA");
        System.out.println("Otrzymal nr: "+demoClass.nr);
        System.out.println("Otrzymal napis: "+demoClass.napis);
    }

    //WYSLANIE DANYCH Z AUTORYZACJA [ADMIN]
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/postAdmin",method = RequestMethod.POST)
    public void postAdmin(@RequestBody DemoClass demoClass){
        System.out.println("METODA POST DOSTEPNA DLA ADMINA");
        System.out.println("Otrzymal nr: "+demoClass.nr);
        System.out.println("Otrzymal napis: "+demoClass.napis);
    }

    //WYSLANIE DANYCH I OCZEKIWANIE ODPOWIEDZI ZALEZNEJ OD TYPU USERA
    @RequestMapping(value = "/postTest",method = RequestMethod.POST)
    public String postTest(@RequestBody DemoClass demoClass,Authentication auth){
        System.out.println("METODA DOSTEPNA DLA WSZYSTKICH");
        SecurityInfo si = new SecurityInfo(auth,userAccountService); //klasa z informacja o uzytkowniku wywolujacym akcje

        if(!si.isLogged()){
            System.out.println("WYWOLUJE GOSC");
            return "Jestem gosciem, ktory przeslal napis: "+demoClass.getNapis();
        }
        else if(si.isUser()){
            System.out.println("WYWOLUJE USER "+si.getUsername());
            return "Jestem userem "+si.getUsername()+", ktory przeslal napis: "+demoClass.getNapis();
        }
        else if (si.isAdmin()){
            System.out.println("WYWOLUJE ADMIN "+si.getDetails().getEmail());
            return "Jestem adminem o nazwisku "+si.getDetails().getLastname()+", ktory przeslal napis: "+demoClass.getNapis();
        }
        return null;
    }

    //UPLOAD PLIKOW Z AUTORYZACJA [USER]
    private static int nrPliku = 0;
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/postFile",method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println("Upload pliku");
        System.out.println(file.getName());
        if(!file.isEmpty()){
            try{
                byte[] bytes = file.getBytes();

                //zapis do bazy danych
                eventImageService.saveImageToEvent(1,bytes);

                /* DLA ZAPISU NA LOKALNYM DYSKU
                File serverFile = new File("D:\Projekt PZ - Eventer\eventer\\uploadTest"    //ZMIENIC NA SWOJA SCIEZKE DLA ZAPISU UPLOADOWANYCH PLIKO!!!
                        + File.separator  + nrPliku + ".jpg");
                nrPliku++;
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                */
            }catch (Exception e){
                System.out.println("uploadImage() Exception ");
            }
        }
    }

    //Odczyt obrazka z bazy
    @RequestMapping(value = "/getImage",method = RequestMethod.GET)
    public void findImage(HttpServletResponse resp){
        Path path = FileSystems.getDefault().getPath("","C:\\Users\\Wojciech\\IdeaProjects\\eventer\\src\\main\\resources\\images\\stock.jpg");

        try{
            byte [] dbImage = null;
            dbImage = eventImageService.findImageByIdEvent(1);
            if(dbImage==null){ // brak obrazka = stockowy obrazek z dysku
                dbImage = Files.readAllBytes(path);
            }
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(dbImage);
        }
        catch (IOException ioe){
            System.out.println("IOException");
        }
    }


}