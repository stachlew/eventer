package pl.wat.logic.event.search;


import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.EventSearchRepository;
import pl.wat.logic.event._model.*;
import pl.wat.logic.event.dashboard.EventDashboardStatisticsService;

import java.util.LinkedList;
import java.util.List;
@Service
public class EventSearchService {

    static final int sizeOfPage =5;

    @Autowired
    EventSearchRepository eventSearchRepository;
    @Autowired
    EventExpressions eventExpressions;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventDashboardStatisticsService eventDashboardStatisticsService;

    public List<EventAdministrationSearchResult> findAdministrationEventsPage(EventAdministrationSearchForm form){
        Predicate predicate = eventExpressions.createPredicateDependsOfEventSearchForm(form);
        Page<Event> eventPage = eventSearchRepository.findAll(predicate,new PageRequest(form.getSiteNo(),sizeOfPage));
        List<Event> eventList = eventPage.getContent();
        List<EventAdministrationSearchResult> resultList = new LinkedList<>();

        for (Event e:eventList){
            //pobierz ilosc zapisanych
            int registeredGuests= eventDashboardStatisticsService.getStatistics(e.getIdEvent()).getParticipants();
            EventAdministrationSearchResult eventResult = new EventAdministrationSearchResult(
                    e.getIdEvent(),
                    e.getTitle(),
                    e.getPlace().getCity().getCityName(),
                    e.getStartTime(),
                    e.getEndTime(),
                    e.getCapacity(),
                    e.getVisits(),
                    new SimpleUser(e.getUser().getId(), e.getUser().getUsername()),
                    e.isPublished(),
                    registeredGuests
            );
            resultList.add(eventResult);
        }

        return resultList;
    }

    public List<EventSearchResult> findEventsPage(EventSearchForm form,int nrPage){
        Predicate predicate = eventExpressions.createPredicateDependsOfEventSearchForm(form);
        Page<Event> eventPage = eventSearchRepository.findAll(predicate,new PageRequest(nrPage,sizeOfPage));
        List<Event> eventList = eventPage.getContent();
        List<EventSearchResult> resultList = new LinkedList<>();

        for (Event e:eventList){
            //pobierz ilosc zapisanych
            int registeredGuests= eventDashboardStatisticsService.getStatistics(e.getIdEvent()).getParticipants();
            EventSearchResult eventResult = new EventSearchResult(e,e.getUser(),e.getPlace(),e.getEventType(),e.getPlace().getCity(),e.getPlace().getCity().getRegion(),registeredGuests);
            resultList.add(eventResult);
        }
        return resultList;
    }

    public List<EventSearchResult> findEventsFull(EventSearchForm form){
        Predicate predicate = eventExpressions.createPredicateDependsOfEventSearchForm(form);
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
