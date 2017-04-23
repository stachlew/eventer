package pl.wat.api.event.view.opinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventViewOpinion;
import pl.wat.logic.event._model.EventViewOpinionForm;
import pl.wat.logic.event.view.EventOpinionService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/event/view/opinion")
public class EventOpinionController {

    @Autowired
    EventOpinionService eventOpinionService;

    @RequestMapping(value = "/postNewOpinion",method = RequestMethod.POST)
    public boolean createOpinion(@RequestBody EventViewOpinionForm form){
        if(form!=null){
            form.setCreateDate(new Timestamp(System.currentTimeMillis()));
            return eventOpinionService.createOpinion(form);
        }
        return false;
    }

    @RequestMapping(value = "/getOpinions/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<EventViewOpinion> getEventViewDetails(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            return eventOpinionService.getOpinionsByIdEvent(intId);
        }catch (NumberFormatException e){
            return null;
        }
    }


}
