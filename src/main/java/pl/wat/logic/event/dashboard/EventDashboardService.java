package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.location.PlaceRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardInfo;

@Service
public class EventDashboardService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PlaceRepository placeRepository;

    //Zwrot pelnego Eventu z bazy
    public Event getFullEvent(int id){
        if(eventRepository.exists(id)) {
            return eventRepository.getOne(id);
        }
        return null;
    }

    // Zwrot informacji o wydarzeniu
    public EventDashboardInfo getEventDashboardInfo(int id) {
        if(eventRepository.exists(id)) {
            Event event = eventRepository.getOne(id);
            Place place = event.getPlace();
            EventType eventType = event.getEventType();

            return new EventDashboardInfo(event, place, eventType);
        }
        return null;
    }

    public boolean editEvent(EventDashboardInfo eventDashboardInfo) {
        Event event = eventRepository.getOne(eventDashboardInfo.getIdEvent());

        event.setCapacity(eventDashboardInfo.getCapacity());
        event.setDescription(eventDashboardInfo.getDescription());
        event.setEndTime(eventDashboardInfo.getEndTime());
        event.setFreeEntrance(eventDashboardInfo.isFreeEntrance());
        event.setPublished(eventDashboardInfo.isPublished());
        event.setRegisterEnabled(eventDashboardInfo.isRegisterEnabled());
        event.setStartTime(eventDashboardInfo.getStartTime());
        event.setTitle(eventDashboardInfo.getTitle());
        event.setYoutubeLink(eventDashboardInfo.getYoutubeLink());

        event.setEventType(eventDashboardInfo.getEventType());

        try {
            eventRepository.save(event);
            placeRepository.save(eventDashboardInfo.getPlace());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
