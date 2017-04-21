package pl.wat.api.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventHeader;
import pl.wat.logic.event._model.EventViewDetails;
import pl.wat.logic.event.view.EventViewService;

import java.util.List;

@RestController
@RequestMapping("/api/event/view")
public class EventViewController {
    @Autowired
    EventViewService eventViewService;


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
            return eventViewService.getEventViewDetails(intId);
        }catch (NumberFormatException e){
            return null;
        }
    }

}
