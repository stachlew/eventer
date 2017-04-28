package pl.wat.logic.event.search;


import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventSearchRepository;
import pl.wat.logic.event._model.EventSearchForm;
import pl.wat.logic.event._model.EventSearchResult;
import pl.wat.logic.event.dashboard.EventDashboardStatisticsService;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventSearchService {
    @Autowired
    EventSearchRepository eventSearchRepository;

    @Autowired
    EventDashboardStatisticsService eventDashboardStatisticsService;

    public List<EventSearchResult> findEvents(EventSearchForm form){
        Predicate predicate = EventExpressions.createPredicateDependsOfEventSearchForm(form);
        Iterable<Event> eventList = eventSearchRepository.findAll(predicate);
        List<EventSearchResult> resultList = new LinkedList<>();

        for (Event e:eventList){
            //pobierz ilosc zapisanych
            int registeredGuests= eventDashboardStatisticsService.getStatistics(e.getIdEvent()).getParticipants();
            EventSearchResult eventResult = new EventSearchResult(e,e.getUser(),e.getPlace(),e.getEventType(),e.getPlace().getCity(),e.getPlace().getCity().getRegion(),registeredGuests);
            resultList.add(eventResult);
        }
        return resultList;
    }
}
