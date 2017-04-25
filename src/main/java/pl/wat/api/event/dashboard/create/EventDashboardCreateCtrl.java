package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventStatus;
import pl.wat.db.domain.event.Template;
import pl.wat.db.domain.event.location.Place;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @PostMapping
    public int CreateEvent(@RequestBody EventCreateForm ev) {
        Event event = new Event.EventBuilder()
                .title(ev.getTitle())
                .eventStatus(EventStatus.PUBLISHED_AND_NOT_AVAILABLE)
                .template(new Template(1, "Szablon1"))
                .published(true)
                .registerEnabled(true)
                .visits(0)
                .capacity(ev.getCapacity())
                .createDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
                .description(ev.getDescription())
                .startTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
                .endTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))
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
        Event saved = eventCreateService.save(event);
        return saved.getIdEvent();
    }
}
