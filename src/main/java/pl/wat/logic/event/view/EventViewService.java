package pl.wat.logic.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.domain.event.lecture.Speaker;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.logic.event._model.EventHeader;
import pl.wat.logic.event._model.view.EventViewDetails;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventViewService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    SpeakerRepository speakerRepository;


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
            List<Integer> speakersId = speakerRepository.getDistinctIdSpeakersByIdEvent(id);
            List<Speaker> speakers=new LinkedList<>();
            for (Integer i: speakersId) {
                speakers.add(speakerRepository.getOne(i));
            }
            List<Lecture> lectures =lectureRepository.getAllByEventOrderByStartTime(event);
            eventRepository.incrementVisit(id);
            return new EventViewDetails(event,lectures,speakers);
        }
        return null;
    }


    public List<EventHeader> getLatest(){
        List<EventHeader> latest = new LinkedList<>();
        List<Event> events = eventRepository.findTopNOfLatestEvents(8);
        for (Event e: events) {
            latest.add(new EventHeader(e));
        }
        return latest;
    }

    public List<EventHeader> getByUser(User user){
        List<EventHeader> byUser = new LinkedList<>();
        List<Event> events;
        if(user!=null) {
            events = eventRepository.findAllByUserOrderByIdEvent(user);
            for (Event e : events) {
                byUser.add(new EventHeader(e));
            }
        }
        return byUser;
    }

    public List<EventHeader> getLastMinuteEvent() {
        List<Event> lastMinuteEvents = eventRepository.findTopNOfLatestEvents(4);
        List<EventHeader> resultList = new LinkedList<>();


        for (Event e: lastMinuteEvents) {
            EventHeader eventHeader = new EventHeader(
                    e.getIdEvent(),
                    e.getTitle(),
                    e.getDescription(),
                    e.getStartTime(),
                    e.getEndTime()
            );
            resultList.add(eventHeader);
        }
        return resultList;
    }

    public List<EventHeader> getMostVisitedEvent() {
        List<Event> mostVisitedEvent = eventRepository.findTop4ByOrderByVisitsDesc();
        List<EventHeader> resultList = new LinkedList<>();


        for (Event e: mostVisitedEvent) {
            EventHeader eventHeader = new EventHeader(
                    e.getIdEvent(),
                    e.getTitle(),
                    e.getDescription(),
                    e.getStartTime(),
                    e.getEndTime()
            );
            resultList.add(eventHeader);
        }
        return resultList;
    }

}
