package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardAttendForm;
import pl.wat.logic.event._model.view.EventViewPartcipant;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventDashboardParticipantsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    // Zwrot uczestników wydarzenia
    public List<EventViewPartcipant> getParticipants(int id) {
        if(eventRepository.exists(id)) {
            List<EventViewPartcipant> eventParticipants = new LinkedList<>();
            Event event = eventRepository.getOne(id);
            List<Participant> participants = participantRepository.findAllByEvent(event);
            for(Participant participant : participants) {
                eventParticipants.add(new EventViewPartcipant(participant));
            }

            return eventParticipants;
        }
        return null;
    }

    public boolean editParticipantPresence(EventViewPartcipant eventViewPartcipant) {
        Participant participant = participantRepository.findOne(eventViewPartcipant.getIdPartcipant());
        participant.setPresence(eventViewPartcipant.isPresence());

        try {
            participantRepository.save(participant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
