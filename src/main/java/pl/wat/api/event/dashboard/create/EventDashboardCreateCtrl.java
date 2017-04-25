package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.repository.event.EventTypeRepository;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @PostMapping
    public int CreateEvent(@RequestBody EventCreateForm eventCreateForm) {
        Event event = new Event.EventBuilder()
                .eventType(eventTypeRepository.findByEventTypeName(eventCreateForm.getEventTypeName()))
                .place(new Place.PlaceBuilder.)
                .createEvent();
        Event saved = eventCreateService.createEvent(event);
        return saved.getIdEvent();
    }
}
