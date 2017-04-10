package pl.wat.api.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wat.db.domain.Customer;
import pl.wat.db.domain.DemoClass;
import pl.wat.db.domain.user.User;
import pl.wat.logic.CustomerService;
import pl.wat.logic.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

//KONTROLER DEMO typu Ctrl+c, Ctrl+v dla dalszych metod kontrolerow w projekcie
@RestController
@RequestMapping("/api")
public class DemoRestController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    //METODA DO SZYBKIEGO TESTOWANIA SERWISOW [ wchodzic na: http://localhost:8080/api/test ]
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody String getTest(){
        System.out.println("URUCHOMIENIE TESTU");
        boolean status1=false;
        boolean status2=false;
        //Deklaracja

        User newUser = new User("test","1234","Adam","Nowak","nowak@wp.pl","425754243");
        //User newAdmin = new User("test","1234","Admin","Nowak","nowak@wp.pl","425754243");

        //Wykonywanie
        status1 = userService.createUser(newUser);
        status2 = userService.deleteUser("user");


        //Zwrot wyniku
        if(status1 && status2)
            return "OK!";
        else
            return "FAIL";
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
                File serverFile = new File("D:\\Projekty\\PzPro\\uploadTest"    //ZMIENIC NA SWOJA SCIEZKE DLA ZAPISU UPLOADOWANYCH PLIKO!!!
                        + File.separator  + nrPliku + ".jpg");
                nrPliku++;
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            }catch (Exception e){
                System.out.println("uploadImage() Exception ");
            }
        }
    }


}
