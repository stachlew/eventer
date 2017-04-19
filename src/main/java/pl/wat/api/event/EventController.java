package pl.wat.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wat.db.domain.event.Event;
import pl.wat.logic.EventService;
import pl.wat.logic.model.Event.EventHeader;

import java.util.List;

@Controller
@RequestMapping(value = "/api/events")
public class EventController {


    @Autowired
    EventService eventService;


    //zwrotka najnowszych
    @RequestMapping(value = "/getLatest",method = RequestMethod.GET)
    public @ResponseBody List<EventHeader> getLatest(){
        return eventService.getLatest();
    }

}
