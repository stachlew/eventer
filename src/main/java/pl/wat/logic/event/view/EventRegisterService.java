package pl.wat.logic.event.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.ParticipantForm;
import pl.wat.logic.util.MailContainer;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
                return false;
            }
            return true;
        }
       return false;
    }
}
