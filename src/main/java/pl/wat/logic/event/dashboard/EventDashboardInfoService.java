package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardInfo;

@Service
public class EventDashboardInfoService {

    @Autowired
    EventRepository eventRepository;

    // Zwrot informacji o wydarzeniu
    public EventDashboardInfo getEventDashboardInfo(int id) {
        if(eventRepository.exists(id)) {
            Event event = eventRepository.getOne(id);
            Place place = event.getPlace();
            EventType eventType = event.getEventType();
            City city = place.getCity();
            Region region = city.getRegion();

            return new EventDashboardInfo(event, place, eventType, city, region);
        }
        return null;
    }

}
