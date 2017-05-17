package pl.wat.api.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventHeader;
import pl.wat.logic.event._model.view.EventViewDetails;
import pl.wat.logic.event.view.EventViewService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/event/view")
public class EventViewController {
    @Autowired
    EventViewService eventViewService;

    @Autowired
    UserAccountService userAccountService;


    //zwrotka najnowszych
    @RequestMapping(value = "/getLatest",method = RequestMethod.GET)
    public @ResponseBody
    List<EventHeader> getLatest(){
        return eventViewService.getLatest();
    }

    //zwrot detali do podgladu eventu wg id
    @RequestMapping(value = "/getEventDetails/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EventViewDetails getEventViewDetails(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            EventViewDetails eventViewDetails = eventViewService.getEventViewDetails(intId);
            return eventViewDetails;
        }catch (NumberFormatException e){
            return null;
        }
    }

    //zwrotka uzytkownika
    @RequestMapping(value = "/getByUser",method = RequestMethod.GET)
    public @ResponseBody
    List<EventHeader> getByUser(Authentication auth){
        SecurityInfo si = new SecurityInfo(auth,userAccountService);
        return eventViewService.getByUser(si.getDetails());
    }



}
