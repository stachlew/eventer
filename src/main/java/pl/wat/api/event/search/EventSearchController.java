package pl.wat.api.event.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;
import pl.wat.logic.event.search.EventSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/event/search")
public class EventSearchController {

    @Autowired
    EventSearchService eventSearchService;

    //zwrotka najnowszych dla wyszukiwarki
    @RequestMapping(value = "/getLatest",method = RequestMethod.GET)
    public @ResponseBody
    List<EventSearchResult> getSearchLatest(){
        EventSearchForm form = new EventSearchForm();
        //form.setRegisterEnabled(true);
        return eventSearchService.findEvents(form);
    }

    @RequestMapping(value = "/getByCriteria",method = RequestMethod.POST)
    public @ResponseBody
    List<EventSearchResult> getSearchByForm(@RequestBody EventSearchForm form){
        return eventSearchService.findEvents(form);
    }
}
