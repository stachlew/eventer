package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.Opinion;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.domain.event.image.EventImage;
import pl.wat.db.domain.event.image.SpeakerImage;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.OpinionRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.db.repository.event.image.EventImageRepository;
import pl.wat.db.repository.event.image.SpeakerImageRepository;
import pl.wat.db.repository.event.lecture.LectureRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.db.repository.event.location.PlaceRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardInfo;

import java.util.List;

@Service
public class EventDashboardService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    EventImageRepository eventImageRepository;

    @Autowired
    SpeakerImageRepository speakerImageRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    OpinionRepository opinionRepository;

    @Autowired
    ParticipantRepository participantRepository;

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

    @Transactional
    public boolean deleteEvent(int idEvent){
        Event event = eventRepository.findOne(idEvent);
        if(event!=null) {
            //////Usuwanie zdjeć eventu
            EventImage eventImage = eventImageRepository.findByIdEvent(idEvent);
            if (eventImage != null) {
                eventImageRepository.delete(eventImage);
            }
            ////Usuwanie zdjęć speakerów
            List<Integer> distinctIdSpeakersByIdEvent = speakerRepository.getDistinctIdSpeakersByIdEvent(idEvent);
            for (Integer i : distinctIdSpeakersByIdEvent) {
                SpeakerImage speakerImage = speakerImageRepository.findByIdSpeaker(i);
                if (speakerImage != null) {
                    speakerImageRepository.delete(speakerImage);
                }
            }
            //////Usuwanie speakerów
            for (Integer i : distinctIdSpeakersByIdEvent) {
                speakerRepository.delete(i);
            }
            ////////Usuwanie wykładów
            List<Lecture> allLecture = lectureRepository.findAllByEvent(event);
            for (Lecture lecture:allLecture) {
                lectureRepository.delete(lecture);
            }
            ///////Usuwanie opinni
            List<Opinion> allOpinion = opinionRepository.findAllByEvent(event);
            for (Opinion opinion: allOpinion) {
                opinionRepository.delete(opinion);
            }
            //////Usuwanie obecności
            List<Participant> allParticipant = participantRepository.findAllByEvent(event);
            for (Participant participant:allParticipant) {
                participantRepository.delete(participant);
            }

            eventRepository.delete(event);
            return true;
        }
        return false;

    }

}
