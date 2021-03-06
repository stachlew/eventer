package pl.wat.api.event.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventHeader;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;
import pl.wat.logic.event.search.EventSearchService;
import pl.wat.logic.event.view.EventViewService;

import java.util.List;

@RestController
@RequestMapping("/api/event/search")
public class EventSearchController {

    @Autowired
    EventSearchService eventSearchService;
    @Autowired
    EventViewService eventViewService;

    //zwrotka najnowszych dla wyszukiwarki
    @RequestMapping(value = "/getLatest",method = RequestMethod.GET)
    public @ResponseBody
    List<EventSearchResult> getSearchLatest(){
        EventSearchForm form = new EventSearchForm();
        //form.setRegisterEnabled(true);
        return eventSearchService.findEventsPage(form,form.getSiteNo());
    }

    //Wyszukiwanie wg krytetiow wyszukiwania z paginacja
    @RequestMapping(value = "/getSearchPage",method = RequestMethod.POST)
    public @ResponseBody
    List<EventSearchResult> getSearchPage(@RequestBody EventSearchForm form){
        return eventSearchService.findEventsPage(form,form.getSiteNo());
    }

    //Wyszukiwanie wg krytetiow wyszukiwania bez paginacji
    @RequestMapping(value = "/getSearchFull",method = RequestMethod.POST)
    public @ResponseBody
    List<EventSearchResult> getSearchFull(@RequestBody EventSearchForm form){
        return eventSearchService.findEventsFull(form);
    }

    @RequestMapping(value = "/getLastMinute",method = RequestMethod.GET)
    public @ResponseBody
    List<EventHeader> getLastMinuteEvent(){
        return eventViewService.getLastMinuteEvent();
    }

    @RequestMapping(value = "/getMostVisited",method = RequestMethod.GET)
    public @ResponseBody
    List<EventHeader> getMostVisitedEvent(){
        return eventViewService.getMostVisitedEvent();
    }

}
