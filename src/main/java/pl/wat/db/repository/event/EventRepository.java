package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wat.db.domain.event.Event;

import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query(value = "select * from (select * from eve_events order by create_date desc) where rownum < :limit",
            nativeQuery = true)
    List<Event> findTopNOfLatestEvents(@Param("limit") int limit);
}
