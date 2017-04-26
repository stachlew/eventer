package pl.wat.logic.event.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.location.PlaceRepository;
import pl.wat.logic.event._model.EventCreateForm;

import java.sql.Timestamp;

@Service
public class EventCreateService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public int processAndCreateEvent(EventCreateForm form, User user){
        Event event = new Event.EventBuilder()
                .title(form.getTitle())
                .description(form.getDescription())
                .eventType(form.getEventType())
                .startTime(form.getStartTime())
                .endTime(form.getEndTime())
                .createDate(new Timestamp(System.currentTimeMillis()))
                .capacity(form.getCapacity())
                .freeEntrance(form.isFreeEntrance())
                .visits(0)
                .registerEnabled(true)
                .published(true) //TODO: niepublikowane, przekierowac na strone edycji gdy ta bedzie zrobiona
                .user(user)
                .place(new Place.PlaceBuilder()
                        .streetName(form.getStreetName())
                        .streetNo(form.getStreetNo())
                        .geoLength(form.getGeoLength())
                        .geoWidth(form.getGeoWidth())
                        .city(form.getCity())
                        .build())
                .build();

        Event saved = this.save(event);
        if(saved!=null)
            return saved.getIdEvent();
        else
            return -1;
    }


    public Event save(Event event){
        if(event!=null){
            placeRepository.save(event.getPlace());
            Event saved = eventRepository.save(event);
            if(saved!=null) return saved;
        }
        return null;
    }


}
