package pl.wat.api.administration.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.db.domain.event.Event;
import pl.wat.logic.administration.events.AdministrationEventsService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/events")
public class AdministrationEventsController {

  @Autowired
  private AdministrationEventsService administrationEventsService;

  @GetMapping("/getEventsByUserName/{username}")
  @ResponseBody
  public List<Event> getEventsByUserName(@PathVariable String username) {
    return administrationEventsService.getEventsByUserName(username);
  }

  @GetMapping("/getAllEvents")
  @ResponseBody
  public List<Event> getAllEvents() {
    return administrationEventsService.getEvents();
  }

  @GetMapping("/getEvent/{id}")
  @ResponseBody
  public Event getEvent(@PathVariable int id) {
    return administrationEventsService.getEvent(id);
  }

  @PostMapping("/{id}/publishedUp")
  public void publishedUp(@PathVariable int id) {
    administrationEventsService.setPublished(true, id);
  }

  @PostMapping("/{id}/publishedDown")
  public void publishedDown(@PathVariable int id) {
    administrationEventsService.setPublished(false, id);
  }

  @PostMapping("/{id}/registerEnabled")
  public void registerEnabled(@PathVariable int id) {
    administrationEventsService.setRegisterEnabled(true, id);
  }

  @PostMapping("/{id}/registerDisabled")
  public void registerDisabled(@PathVariable int id) {
    administrationEventsService.setRegisterEnabled(false, id);
  }
}
