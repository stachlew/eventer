package pl.wat.logic.event.search;


import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.api.event.search.EventExpressions;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.EventSearchRepository;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventSearchService {
    @Autowired
    EventSearchRepository eventSearchRepository;

    @Autowired
    EventRepository eventRepository;

    public Iterable<Event> searchEvent(String title){
        Predicate predicate = EventExpressions.hasTitle(title);
        return eventSearchRepository.findAll(predicate);


    }
    //TODO: PODPIAC PORZADNE WYSZUKIWANIE WG POL FORMULARZA
    public List<EventSearchResult> findDemo(EventSearchForm form){
        List<Event> eventList = eventRepository.findAll();
        List<EventSearchResult> resultList = new LinkedList<>();

        for (Event e:eventList){
            //pobierz ilosc zapisanych
            int registeredGuests=0;
            EventSearchResult eventResult = new EventSearchResult(e,e.getUser(),e.getPlace(),e.getEventType(),e.getPlace().getCity(),e.getPlace().getCity().getRegion(),registeredGuests);
            resultList.add(eventResult);
        }
        return resultList;
    }
}
