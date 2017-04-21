package pl.wat.api.images;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.event.image.EventImageService;

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

    //Odczyt obrazka eventu z bazy
    @RequestMapping(value = "/getEventImage/{id}",method = RequestMethod.GET)
    public void getEventImage(HttpServletResponse resp, @PathVariable String id){
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }
        //default
        Path path = FileSystems.getDefault().getPath("","D:\\Projekty\\LocalRepoEventer\\eventer\\src\\main\\resources\\images\\stock.jpg");

        try{
            byte [] dbImage = null;
            dbImage = eventImageService.findImageByIdEvent(intId);
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

    //Odczyt obrazka mowcy z bazy
    @RequestMapping(value = "/getSpeakerImage/{id}",method = RequestMethod.GET)
    public void getSpeakerImage(HttpServletResponse resp, @PathVariable String id){
        System.out.println("getSpeakerImage(): "+id);
        int intId=-1;
        try {
            intId = Integer.parseInt(id);
        }catch (NumberFormatException e){
        }
        //default
        Path path = FileSystems.getDefault().getPath("","D:\\Projekty\\LocalRepoEventer\\eventer\\src\\main\\resources\\images\\defaultProfile.png");

        try{
            byte [] dbImage = null;
            dbImage = eventImageService.findImageByIdSpeaker(intId);
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
