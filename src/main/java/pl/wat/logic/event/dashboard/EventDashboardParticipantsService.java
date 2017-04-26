package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.EventDashboardParticipantsInfo;

import java.util.List;

@Service
public class EventDashboardParticipantsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    // Zwrot uczestników wydarzenia
    public EventDashboardParticipantsInfo getParticipants(int id) {
        if(eventRepository.exists(id)) {
            Event event = eventRepository.getOne(id);
            int eventId = event.getIdEvent();
            List<Participant> participants = participantRepository.findAllByEvent(event);

            return new EventDashboardParticipantsInfo(participants, eventId);
        }
        return null;
    }
}
