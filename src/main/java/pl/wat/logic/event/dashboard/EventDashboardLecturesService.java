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
            List<Lecture> lectures = lectureRepository.findAllByEvent(event);
            for(Lecture lecture : lectures) {
                eventLectures.add(new EventViewLecture(lecture));
            }

            return eventLectures;
        }
        return null;
    }

    public boolean editLecture(EventViewLecture eventLecture) {
        Lecture lecture = lectureRepository.findOne(eventLecture.getIdLecture());

        lecture.setDescription(lecture.getDescription());
        lecture.setStartTime(lecture.getStartTime());
        lecture.setEndTime(lecture.getEndTime());
        lecture.setLectureName(lecture.getLectureName());
        lecture.setSpeaker(lecture.getSpeaker());

        try {
            lectureRepository.save(lecture);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addLecture(EventViewLecture eventLecture) {
        Lecture lecture = new Lecture();

        lecture.setDescription(lecture.getDescription());
        lecture.setStartTime(lecture.getStartTime());
        lecture.setEndTime(lecture.getEndTime());
        lecture.setLectureName(lecture.getLectureName());
        lecture.setSpeaker(lecture.getSpeaker());
        lecture.setEvent(lecture.getEvent());

        try {
            lectureRepository.save(lecture);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteLecture(EventViewLecture eventLecture) {
        try {
            lectureRepository.delete(eventLecture.getIdLecture());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
