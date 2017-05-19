package pl.wat.logic.administration.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.logic.util.MailContainer;

import java.util.List;

@Service
public class AdministrationEventsService {

  @Autowired
  private EventRepository eventRepository;

  public void sendMail(String mailTo, String subject, String message){
    MailContainer mailContainer = new MailContainer();
    mailContainer.sendMail("mail@eventer.pl",mailTo,subject,message);
  }

  public List<Event> getEventsByUserName(String username) {
    return eventRepository.findEventsByUser_Username(username);
  }

  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  public Event getEvent(int id) {
    return eventRepository.findOne(id);
  }

  public void setPublished(int idEvent, String message) {
    Event event = eventRepository.findOne(idEvent);
    event.setPublished(false);
    if(eventRepository.save(event)!=null){
      System.out.println("START");
      sendMail(event.getUser().getEmail(),"Blokada widoczności wydarzenia : "+event.getTitle(),
              "Wydarzenie: "+event.getTitle()+" zostało zablokowane."
                      + "\n Powód: "
                      + message
                      + "\n \nPozdrawiamy, \n Zespół EVenter");
    }
    System.out.println("STOP");
  }

  public void setRegisterEnabled(boolean flag, int id) {
    Event event = eventRepository.findOne(id);
    event.setRegisterEnabled(flag);
    eventRepository.save(event);
  }
}
