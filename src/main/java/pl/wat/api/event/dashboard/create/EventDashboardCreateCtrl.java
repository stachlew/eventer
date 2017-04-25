package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.location.Place;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @PostMapping
    public int CreateEvent(@RequestBody EventCreateForm ev) {
        Event event = new Event.EventBuilder()
                .capacity(ev.getCapacity())
                .description(ev.getDescription())
                .startTime(ev.getStartTime())
                .endTime(ev.getEndTime())
                .freeEntrance(ev.isFreeEntrance())
                .eventType(ev.getEventType())
                .place(new Place.PlaceBuilder()
                        .streetName(ev.getStreetName())
                        .streetNo(ev.getStreetNo())
                        .geoLength(ev.getGeoLength())
                        .geoWidth(ev.getGeoWidth())
                        .city(ev.getCity())
                        .build())
                .build();
        //Event saved = eventCreateService.save(event);
        System.out.println(event.toString());
        return event.getIdEvent();
    }
}
