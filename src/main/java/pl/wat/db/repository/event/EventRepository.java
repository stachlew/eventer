package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query(value = "select * from (select * from eve_events order by create_date desc) where rownum <= :limit",
            nativeQuery = true)
    List<Event> findTopNOfLatestEvents(@Param("limit") int limit);


    //Metoda zwraca BigDecimal, mimo że powinna Integera- z tego powodu gdy otrzymamy liste numerów, należy
    //zrobić for eacha dla tej listy i dla każdego elementu wywołać metodę z repozytorium findOne(i.intValue())
    @Query(value = "select id_event from (select e.id_event, e.CAPACITY-count(p.id_participant) from eve_events e LEFT JOIN EVE_PARTICIPANTS p ON e.id_event=p.id_event \n" +
            "where  e.published = 1 and e.REGISTER_ENABLED =1   group by e.id_event,e.CAPACITY order by 2 )\n" +
            "where ROWNUM<=:limit",nativeQuery = true)
    List<BigDecimal> findTopNIdEventsWithLeastAmountOfFreeSeats(@Param("limit") int limit);


    List<Event> findTop3ByOrderByVisitsDesc();

    List<Event> findEventsByUser_Username(String username);

    @Modifying
    @Transactional
    @Query(value = "update eve_events set visits=visits+1 where id_event=:id_event", nativeQuery = true)
    void incrementVisit(@Param("id_event") int idEvent);

    @Query("select sum(e.visits) from Event e")
    long getCountVisits();

    @Query("select avg(e.visits) from Event e")
    double getAvgVisits();

    @Query("select count(e.idEvent) from Event e")
    long getCountEvents();

}
