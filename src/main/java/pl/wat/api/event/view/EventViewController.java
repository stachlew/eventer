package pl.wat.api.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.event._model.EventHeader;
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

}
