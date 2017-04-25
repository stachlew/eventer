package pl.wat.api.event.dashboard.create;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.Region;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.util.DictionaryService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private DictionaryService dictionaryService;

    @ResponseBody
    @GetMapping("/eventCreateForm")
    public EventCreateForm getEventCreateForm() {
        return new EventCreateForm();
    }

    @ResponseBody
    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return dictionaryService.getAllRegions();
    }

    @ResponseBody
    @GetMapping("/eventTypes")
    public List<EventType> getEventType() {
        return dictionaryService.getAllEventTypes();
    }
}
