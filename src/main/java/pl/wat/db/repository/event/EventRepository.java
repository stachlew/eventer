package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.Event;

/**
 * Created by K on 2017-04-10.
 */
public interface EventRepository extends JpaRepository<Event,Integer> {
}
