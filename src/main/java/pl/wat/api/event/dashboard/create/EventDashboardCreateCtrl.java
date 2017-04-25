package pl.wat.api.event.dashboard.create;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.event._model.EventCreateForm;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @ResponseBody
    @GetMapping("/eventCreateForm")
    public EventCreateForm getEventCreateForm() {
        return new EventCreateForm();
    }
}
