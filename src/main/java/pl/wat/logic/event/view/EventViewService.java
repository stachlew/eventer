package pl.wat.logic.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.logic.event._model.EventHeader;
import pl.wat.logic.event._model.EventViewDetails;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventViewService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LectureRepository lectureRepository;


    //odczyt wydarzenia --demo
    public Event getEvent(int id){
        if(eventRepository.exists(id)){
            return eventRepository.getOne(id);
        }
        return null;
    }

    //zwrot detali wydarzenia na potrzeby podgladu
    public EventViewDetails getEventViewDetails(int id){
        if(eventRepository.exists(id)){
            Event event = eventRepository.getOne(id);
            return new EventViewDetails(event,lectureRepository.getAllByEventOrderByStartTime(event));
        }
        return null;
    }

    //TODO: Dorobic logike wyszukiwania najnowszych
    public List<EventHeader> getLatest(){
        List<EventHeader> latest = new LinkedList<>();
        List<Event> events = eventRepository.findTopNOfLatestEvents(7);
        for (Event e: events) {
            latest.add(new EventHeader(e));
        }
        return latest;
    }
}
