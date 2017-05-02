package pl.wat.api.event.dashboard.speakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.view.EventViewLecture;
import pl.wat.logic.event._model.view.EventViewSpeaker;
import pl.wat.logic.event.dashboard.EventDashboardLecturesService;
import pl.wat.logic.event.dashboard.EventDashboardSpeakersService;

import java.util.List;

@RestController
@RequestMapping("/api/event/dashboard/speakers")
public class EventDashboardSpeakersCtrl {

    @Autowired
    EventDashboardSpeakersService eventDashboardSpeakersService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getList/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<EventViewSpeaker> getSpeakers(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            return eventDashboardSpeakersService.getSpeakers(intId);
        }catch (Exception e){
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getSpeaker/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EventViewSpeaker getSpeaker(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            return eventDashboardSpeakersService.getSpeaker(intId);
        }catch (Exception e){
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public @ResponseBody
    boolean editSpeaker(@RequestBody EventViewSpeaker speaker){
        if(speaker!=null){
            return eventDashboardSpeakersService.updateSpeaker(speaker);
        }
        return false;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public @ResponseBody
    boolean createSpeaker(@RequestBody EventViewSpeaker speaker){
        if(speaker!=null){
            return eventDashboardSpeakersService.addSpeaker(speaker);
        }
        return false;
    }




}
