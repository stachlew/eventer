package pl.wat.logic.administration.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;

import java.util.List;

@Service
public class AdministrationEventsService {

  @Autowired
  EventRepository eventRepository;

  public List<Event> getEventsByUserName(String username) {
    return eventRepository.findEventsByUser_Username(username);
  }

  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  public Event getEvent(int id) {
    return eventRepository.findOne(id);
  }

  public void setPublished(boolean flag, int id) {
    Event event = eventRepository.findOne(id);
    event.setPublished(flag);
    eventRepository.save(event);
  }

  public void setRegisterEnabled(boolean flag, int id) {
    Event event = eventRepository.findOne(id);
    event.setRegisterEnabled(flag);
    eventRepository.save(event);
  }
}
