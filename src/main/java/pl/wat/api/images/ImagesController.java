package pl.wat.api.images;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.event.image.EventImageService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Autowired
    EventImageService eventImageService;

    @Autowired
    EventDashboardService eventDashboardService;

    @Autowired
    UserAccountService userAccountService;

    //Odczyt obrazka eventu z bazy
    @RequestMapping(value = "/getEventImage/{id}",method = RequestMethod.GET)
    public void getEventImage(HttpServletResponse resp, @PathVariable String id){
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }

        try{
            byte [] dbImage = null;
            dbImage = eventImageService.findImageByIdEvent(intId);
            if(dbImage==null){ // brak obrazka = stockowy obrazek z dysku
                dbImage = StreamUtils.copyToByteArray(new ClassPathResource("images/stock.jpg").getInputStream());
            }
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(dbImage);
        }
        catch (IOException ioe){
            System.out.println("IOException Event Image");
        }
    }

    //Odczyt obrazka mowcy z bazy
    @RequestMapping(value = "/getSpeakerImage/{id}",method = RequestMethod.GET)
    public void getSpeakerImage(HttpServletResponse resp, @PathVariable String id){
        System.out.println("getSpeakerImage(): "+id);
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }

        try{
            byte [] dbImage = null;
            dbImage = eventImageService.findImageByIdSpeaker(intId);
            if(dbImage==null){ // brak obrazka = stockowy obrazek z dysku
                dbImage = StreamUtils.copyToByteArray(new ClassPathResource("images/defaultProfile.jpg").getInputStream());
            }
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(dbImage);
        }
        catch (IOException ioe){
            System.out.println("IOException Speaker Image");
        }
    }

    //Zapis obrazka eventu
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/postEventImage/{id}",method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void uploadEventImage(@RequestParam("file") MultipartFile file, @PathVariable String id, Authentication auth) {
        System.out.println("Upload pliku");
        System.out.println(file.getName());
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }

        SecurityInfo si = new SecurityInfo(auth,userAccountService);
        if(si.isEventOwner(intId,eventDashboardService)) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    //zapis do bazy danych
                    eventImageService.saveImageToEvent(intId, bytes);
                } catch (Exception e) {
                    System.out.println("uploadEventImage() Exception ");
                }
            }
        }
    }


    //Zapis obrazka speakera
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/postSpeakerImage/{id}",method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void uploadSpeakerImage(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        System.out.println("Upload pliku");
        System.out.println(file.getName());
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                //zapis do bazy danych
                eventImageService.saveImageToSpeaker(intId, bytes);
            } catch (Exception e) {
                System.out.println("uploadSpeakerImage() Exception ");
            }
        }

    }

    //USUNIECIE OBRAZKA EVENTU
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/deleteEventImage/{id}",method = RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void deleteEventImage(@PathVariable String id,Authentication auth) {
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }
        catch (NumberFormatException e){
        }
        System.out.println("id "+id+ " intID: "+intId);
        SecurityInfo si = new SecurityInfo(auth,userAccountService);
        if(si.isEventOwner(intId,eventDashboardService)) {
            eventImageService.deleteImageEvent(intId);
        }
    }


    //USUNIECIE OBRAZKA SPEAKERA
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/deleteSpeakerImage/{id}",method = RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void deleteSpeakerImage(@PathVariable String id,Authentication auth) {
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }
        catch (NumberFormatException e){
        }
        eventImageService.deleteImageSpeaker(intId);

    }

}
