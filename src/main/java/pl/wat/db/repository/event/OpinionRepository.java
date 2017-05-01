package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Opinion;

import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface OpinionRepository extends JpaRepository<Opinion,Integer> {

    public List<Opinion> getAllByEventOrderByCreateDate(Event event);

    Integer countByEventIdEventAndRate(int idEvent,int rate);
}
