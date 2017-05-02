package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.domain.event.lecture.Speaker;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.logic.event._model.view.EventViewLecture;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventDashboardLecturesService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    public List<EventViewLecture> getEventViewLectures(int id) {
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

     public EventViewLecture getEventViewLecture(int id) {
        if(lectureRepository.exists(id)) {
            EventViewLecture eventLecture = new EventViewLecture(lectureRepository.getOne(id));
            return eventLecture;
        }
        return null;
    }

    @Transactional
    public boolean editLecture(EventViewLecture eventLecture) {
        Lecture lecture = lectureRepository.findOne(eventLecture.getIdLecture());
        Speaker speaker;
        if(lecture!=null){
            lecture.setDescription(eventLecture.getDescription());
            lecture.setStartTime(eventLecture.getStartTime());
            lecture.setEndTime(eventLecture.getEndTime());
            lecture.setLectureName(eventLecture.getLectureName());

            if(eventLecture.getEventViewSpeaker().getIdSpeaker()<=0) {
                speaker = new Speaker(eventLecture.getEventViewSpeaker());
            }
            else {
                speaker = speakerRepository.findOne(eventLecture.getEventViewSpeaker().getIdSpeaker());
                if (speaker==null) return false;
            }
        }
        else {
            return false; //brak prelekcji
        }
        try {
            System.out.println("try editLecture");
            if(speaker.getIdSpeaker()<=0)
                lecture.setSpeaker(speakerRepository.save(speaker));
            else
                lecture.setSpeaker(speaker);

            lectureRepository.save(lecture);
            return true;
        } catch (Exception e) {
            System.out.println("excp addLecture");
            e.printStackTrace();
            return false;
        }




    }

    @Transactional
    public boolean addLecture(EventViewLecture eventLecture) {
        System.out.println("addLecture");
        Lecture lecture = new Lecture();
        Speaker speaker;
        Event event = eventRepository.getOne(eventLecture.getIdEvent());
        if(event!=null){
            lecture.setLectureName(eventLecture.getLectureName());
            lecture.setDescription(eventLecture.getDescription());
            lecture.setStartTime(eventLecture.getStartTime());
            lecture.setEndTime(eventLecture.getEndTime());
            lecture.setEvent(event);

            if(eventLecture.getEventViewSpeaker().getIdSpeaker()<=0){
                speaker = new Speaker(eventLecture.getEventViewSpeaker());
            }
            else {
                speaker = speakerRepository.findOne(eventLecture.getEventViewSpeaker().getIdSpeaker());
                if (speaker==null) return false;
            }
        }
        else {
            return false; //brak eventu
        }

        try {
            System.out.println("try addLecture");
            if(speaker.getIdSpeaker()<=0)
                lecture.setSpeaker(speakerRepository.save(speaker));
            else
                lecture.setSpeaker(speaker);

            lectureRepository.save(lecture);
            return true;
        } catch (Exception e) {
            System.out.println("excp addLecture");
            e.printStackTrace();
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
