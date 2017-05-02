package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.lecture.Speaker;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.logic.event._model.view.EventViewSpeaker;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventDashboardSpeakersService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    public List<EventViewSpeaker> getSpeakers(int id) {
        if(eventRepository.exists(id)) {
            List<EventViewSpeaker> speakersInfoList = new LinkedList<>();
            List<Integer> distinctIdSpeakers = speakerRepository.getDistinctIdSpeakersByIdEvent(id);
            for(Integer integer : distinctIdSpeakers) {
                speakersInfoList.add(new EventViewSpeaker(speakerRepository.findOne(integer)));
            }
            return speakersInfoList;
        }
        return null;
    }

    public EventViewSpeaker getSpeaker(int id) {
        return new EventViewSpeaker(speakerRepository.findOne(id));
    }

    public boolean addSpeaker(EventViewSpeaker eventSpeaker) {
        Speaker speaker = new Speaker();

        speaker.setDescription(eventSpeaker.getDescription());
        speaker.setEmail(eventSpeaker.getEmail());
        speaker.setFirstname(eventSpeaker.getFirstname());
        speaker.setLastname(eventSpeaker.getLastname());
        speaker.setPhone(eventSpeaker.getPhone());

        try {
            speakerRepository.save(speaker);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateSpeaker(EventViewSpeaker eventSpeaker) {
        Speaker speaker = speakerRepository.getOne(eventSpeaker.getIdSpeaker());

        speaker.setDescription(eventSpeaker.getDescription());
        speaker.setFirstname(eventSpeaker.getFirstname());
        speaker.setLastname(eventSpeaker.getLastname());
        speaker.setPhone(eventSpeaker.getPhone());
        speaker.setEmail(eventSpeaker.getEmail());

        try {
            speakerRepository.save(speaker);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteLecture(EventViewSpeaker eventSpeaker) {
        try {
            speakerRepository.delete(eventSpeaker.getIdSpeaker());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
