package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.lecture.SpeakerRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardSpeakerInfo;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventDashboardSpeakersService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    public List<EventDashboardSpeakerInfo> getSpeakers(int id) {
        if(eventRepository.exists(id)) {
            List<EventDashboardSpeakerInfo> speakersInfoList = new LinkedList<>();
            List<Integer> distinctIdSpeakers = speakerRepository.getDistinctIdSpeakersByIdEvent(id);
            for(Integer integer : distinctIdSpeakers) {
                speakersInfoList.add(new EventDashboardSpeakerInfo(speakerRepository.findOne(integer)));
            }
            return speakersInfoList;
        }
        return null;
    }
}
