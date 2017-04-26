package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Template;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public int CreateEvent(@RequestBody EventCreateForm ev) {
        Event event = new Event.EventBuilder()
                .title(ev.getTitle())
                .description(ev.getDescription())
                .eventType(ev.getEventType())
                .startTime(ev.getStartTime())
                .endTime(ev.getEndTime())
                .createDate(new Timestamp(System.currentTimeMillis()))
                .capacity(ev.getCapacity())
                .freeEntrance(ev.isFreeEntrance())
                .visits(0)
                .registerEnabled(true)
                .published(true)
                .user(userRepository.findOne(2))
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
