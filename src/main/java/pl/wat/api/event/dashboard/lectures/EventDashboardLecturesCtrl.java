package pl.wat.api.event.dashboard.lectures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.view.EventViewLecture;
import pl.wat.logic.event._model.view.EventViewSpeaker;
import pl.wat.logic.event.dashboard.EventDashboardLecturesService;

import java.util.List;

@RestController
@RequestMapping("/api/event/dashboard/lectures")
public class EventDashboardLecturesCtrl {

    @Autowired
    EventDashboardLecturesService eventDashboardLecturesService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getList/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<EventViewLecture> getLectures(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            return eventDashboardLecturesService.getEventViewLectures(intId);
        }catch (Exception e){
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getLecture/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EventViewLecture getLecture(@PathVariable String id){
        try {
            int intId = Integer.parseInt(id);
            return eventDashboardLecturesService.getEventViewLecture(intId);
        }catch (Exception e){
            return null;
        }
    }



    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public @ResponseBody
    boolean editLecture(@RequestBody EventViewLecture lecture){
        if(lecture!=null){
            return eventDashboardLecturesService.editLecture(lecture);
        }
        return false;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public @ResponseBody
    boolean createLecture(@RequestBody EventViewLecture lecture){
        if(lecture!=null){
            System.out.println("createLecture()");
            return eventDashboardLecturesService.addLecture(lecture);
        }
        return false;
    }


}
