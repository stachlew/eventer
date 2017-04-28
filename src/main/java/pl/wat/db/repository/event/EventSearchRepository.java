package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pl.wat.db.domain.event.Event;


/**
 * Created by K on 2017-04-25.
 */
public interface EventSearchRepository extends JpaRepository<Event,Integer>,QueryDslPredicateExecutor<Event> {

}
