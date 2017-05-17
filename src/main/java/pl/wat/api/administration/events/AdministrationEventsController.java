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
import pl.wat.logic.event.dashboard.EventDashboardService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/events")
public class AdministrationEventsController {

  @Autowired
  private AdministrationEventsService administrationEventsService;

  @Autowired
  private EventDashboardService eventDashboardService;

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

  @PostMapping("/delete/{id}")
  @ResponseBody
  public void deleteEvent(@PathVariable int id) {
    eventDashboardService.deleteEvent(id);
  }

  @PostMapping("/changePublished/{id}")
  public void changePublished(@PathVariable int id) {
    administrationEventsService.setPublished(!administrationEventsService.getEvent(id).isPublished(), id);
  }

  @PostMapping("/changeRegister/{id}")
  public void changeRegister(@PathVariable int id) {
    administrationEventsService.setRegisterEnabled(!administrationEventsService.getEvent(id).isRegisterEnabled(), id);
  }

}
