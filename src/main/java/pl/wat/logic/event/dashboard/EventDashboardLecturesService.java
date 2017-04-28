package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.logic.event._model.view.EventViewLecture;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventDashboardLecturesService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LectureRepository lectureRepository;

    public List<EventViewLecture> getEventViewLecture(int id) {
        if(eventRepository.exists(id)) {
            List<EventViewLecture> eventLectures = new LinkedList<>();
            Event event = eventRepository.getOne(id);
            int eventId = event.getIdEvent();
            List<Lecture> lectures = lectureRepository.findAllByEvent(event);
            for(Lecture lecture : lectures) {
                eventLectures.add(new EventViewLecture(lecture));
            }

            return eventLectures;
        }
        return null;
    }
}
