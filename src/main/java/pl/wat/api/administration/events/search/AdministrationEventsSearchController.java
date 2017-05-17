package pl.wat.api.administration.events.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventAdministrationSearchForm;
import pl.wat.logic.event._model.EventAdministrationSearchResult;
import pl.wat.logic.event.search.EventSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/events/search")
public class AdministrationEventsSearchController {

    @Autowired
    EventSearchService eventSearchService;

    @RequestMapping(value = "/getSearchPage",method = RequestMethod.POST)
    @ResponseBody
    public List<EventAdministrationSearchResult> getEventsByAdminForm(@RequestBody EventAdministrationSearchForm form) {
        List<EventAdministrationSearchResult> administrationEventsPage = eventSearchService.findAdministrationEventsPage(form);

        return administrationEventsPage;
    }

    @RequestMapping(value = "/getSearchFull",method = RequestMethod.POST)
    public @ResponseBody
    List<EventAdministrationSearchResult> getSearchFull(@RequestBody EventAdministrationSearchForm form){
        return eventSearchService.findAdministrationEventsFull(form);
    }

}
