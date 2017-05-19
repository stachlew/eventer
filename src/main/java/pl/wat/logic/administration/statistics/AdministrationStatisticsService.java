package pl.wat.logic.administration.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.administration._model.EventStars;
import pl.wat.logic.event._model.dashboard.EventDashboardStatisticsInfo;
import pl.wat.logic.event.dashboard.EventDashboardStatisticsService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrationStatisticsService {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EventDashboardStatisticsService eventDashboardStatisticsService;

  public long getCountOfEvents() { return eventRepository.getCountEvents();}

  public long getCountOfAllVisits() {
    return eventRepository.getCountVisits();
  }

  public long getAverageOfAllVisits() {
    return eventRepository.getAvgVisits();
  }

  public long getCountOfAllActiveUsers() {
    return userRepository.countByEnabledIsTrue();
  }

  public List<BigDecimal> getSumVisitsForMonth() { return eventRepository.getSumVisitsForMonth(); }
//
//  public List<BigDecimal> getMonthEventCreatedDate() { return eventRepository.getMonthEventCreatedDate(); }

  public List<EventStars> getStarsForLast5Events() {
    List<Event> events = eventRepository.findLast5FinishedEvent();

    List<EventDashboardStatisticsInfo> eventStatistics = events.stream()
            .map(m -> eventDashboardStatisticsService.getStatistics(m.getIdEvent()))
            .collect(Collectors.toList());

    List<EventStars> result = new LinkedList<EventStars>();

    for (int i = 0; i < eventStatistics.size(); i++) {
      EventStars eventStars = new EventStars();
      eventStars.setEventTitle(events.get(i).getTitle());
      eventStars.setStar1(eventStatistics.get(i).getStars1());
      eventStars.setStar2(eventStatistics.get(i).getStars2());
      eventStars.setStar3(eventStatistics.get(i).getStars3());
      eventStars.setStar4(eventStatistics.get(i).getStars4());
      eventStars.setStar5(eventStatistics.get(i).getStars5());
      result.add(eventStars);
    }
    return result;
  }

}
