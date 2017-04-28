package pl.wat.logic.event.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventSearchService {

    @Autowired
    EventRepository eventRepository;


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
