package pl.wat.logic.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.view.ParticipantForm;
import pl.wat.logic.util.MailContainer;

@Service
public class EventRegisterService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    EventRepository eventRepository;

    private JavaMailSender mailSender;
    private SimpleMailMessage templateMessage;



    public void sendEventRegistrationMail(String mailTo, String subject, String message){
        MailContainer mailContainer = new MailContainer();
        mailContainer.sendMail("mail@eventer.pl",mailTo,subject,message);
    }

    public boolean registeredParticipant(ParticipantForm participantForm){
        Participant participant = new Participant(participantForm);
        Participant savedParticipant;
        Event event = eventRepository.getOne(participantForm.getIdEvent());
        if(event!=null) {
            participant.setEvent(event);
            try {
                savedParticipant=participantRepository.save(participant);
                if(savedParticipant!=null){
//WYSYLANIE MEJLA PO REJESTRACJI NA WYDARZENIE

                    sendEventRegistrationMail(participantForm.getEmail(),"Rejestracja na wydarzenie: "+event.getTitle(),
                            "Dokonano rejestracji na wydarzenie "
                            + event.getTitle()
                            + "\nTwój identyfikator gościa: \n"
                            + savedParticipant.getIdParticipant()
                            + "\nPozdrawiamy, \n Zespół EVenter");

                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
       return false;
    }
}
