package pl.wat.api.administration.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.logic.administration.events.AdministrationEventsService;
import pl.wat.logic.event._model.EventAdministrationSearchForm;
import pl.wat.logic.event._model.EventAdministrationSearchResult;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.event.search.EventSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/events")
public class AdministrationEventsController {

  @Autowired
  private AdministrationEventsService administrationEventsService;

  @Autowired
  private EventDashboardService eventDashboardService;

  @Autowired
  private EventSearchService eventSearchService;

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
  public List<EventAdministrationSearchResult> deleteEvent(@PathVariable int id) {
    eventDashboardService.deleteEvent(id);
    return eventSearchService.findAdministrationEventsFull(new EventAdministrationSearchForm("",0));
  }

  @PostMapping("/changePublished/{idEvent}")
  public List<EventAdministrationSearchResult> changePublished(@PathVariable int idEvent, @RequestBody String message) {
    administrationEventsService.setPublished(idEvent,message);
    return eventSearchService.findAdministrationEventsFull(new EventAdministrationSearchForm("",0));
  }

  @PostMapping("/changeRegister/{id}")
  public List<EventAdministrationSearchResult> changeRegister(@PathVariable int id) {
    administrationEventsService.setRegisterEnabled(!administrationEventsService.getEvent(id).isRegisterEnabled(), id);
    return eventSearchService.findAdministrationEventsFull(new EventAdministrationSearchForm("",0));
  }

}
