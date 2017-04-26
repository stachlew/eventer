package pl.wat.db.repository.event.location;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import pl.wat.db.domain.event.Event;

/**
 * Created by K on 2017-04-25.
 */
public interface EventSearchRepository extends QueryDslPredicateExecutor<Event> {
}
