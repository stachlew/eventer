package pl.wat.api.administration.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.api.util.Month;
import pl.wat.logic.administration._model.EventStars;
import pl.wat.logic.administration.statistics.AdministrationStatisticsService;
import pl.wat.logic.util.StatisticService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/administration/statistics")
public class AdministrationStatisticsController {

  @Autowired
  private AdministrationStatisticsService administrationStatisticsService;
  @Autowired
  StatisticService statisticService;

  @GetMapping("/getCountOfEvents")
  public long getCountOfEvents() {
    return administrationStatisticsService.getCountOfEvents();
  }

  @GetMapping("/getCountOfAllVisits")
  public long getCountOfAllVisits() {
    return administrationStatisticsService.getCountOfAllVisits();
  }

  @GetMapping("/getAverageOfAllVisits")
  public long getAverageOfAllVisits() {
    return administrationStatisticsService.getAverageOfAllVisits();
  }

  @GetMapping("/getCountOfAllActiveUsers")
  public long getCountOfAllActiveUsers() { return administrationStatisticsService.getCountOfAllActiveUsers(); }

  @GetMapping("/getCountParticipant")
  public long getCountParticipant() { return statisticService.getCountParticipant(); }

  @GetMapping("/getAvgParticipant")
  public int getAvgParticipant() { return (int) statisticService.getAvgParticipant(); }

  @GetMapping("/getCountEventsInLastMonths")
  public long getCountEventsInLastMonths() { return statisticService.getCountEventsInLastMonths(); }

  @GetMapping("/getCountEventsInThisMonths")
  public long getCountEventsInThisMonths() { return statisticService.getCountEventsInThisMonths(); }

  @GetMapping("/getSumVisitsEventsByMonth")
  public List<BigDecimal> getSumVisitsEventsByMonth() {return administrationStatisticsService.getSumVisitsEventsByMonth();}

  @GetMapping("/getMonthEventCreatedDate")
  public List<String> getMonthEventCreatedDate() {
    List<BigDecimal> decimalList = administrationStatisticsService.getMonthEventCreatedDate();
    List<String> miesiaceList = new ArrayList<>();
    for(BigDecimal decimal : decimalList) {
      System.out.println(decimal);
      miesiaceList.add(Month.parseMiesiac(decimal));
    }
    return miesiaceList;
  }

  @GetMapping("/getStarsForLastFinished")
  public List<EventStars> getAvgStarsForLastFinished() { return administrationStatisticsService.getStarsForLast5Events(); }

}
