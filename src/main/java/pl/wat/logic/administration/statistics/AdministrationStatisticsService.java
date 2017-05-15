package pl.wat.logic.administration.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.user.UserRepository;

@Service
public class AdministrationStatisticsService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  public long getCountOfEvents() {
    return eventRepository.countByIdEvent();
  }

  public long getCountOfAllVisits() {
    return eventRepository.getCountVisits();
  }

  public double getAverageOfAllVisits() {
    return eventRepository.getAvgVisits();
  }

  public long getCountOfAllActiveUsers() {
    return userRepository.countUsersByEnabledIsTrue();
  }
}
