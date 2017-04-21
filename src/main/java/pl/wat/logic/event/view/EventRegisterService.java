package pl.wat.logic.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.ParticipantForm;

@Service
public class EventRegisterService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    EventRepository eventRepository;

    public boolean registeredParticipant(ParticipantForm participantForm){
        Participant participant = new Participant(participantForm);
        Event event = eventRepository.getOne(participantForm.getIdEvent());
        if(event!=null) {
            participant.setEvent(event);
            try {
                participantRepository.save(participant);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
       return false;
    }
}
