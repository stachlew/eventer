package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Participant;

import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface ParticipantRepository extends JpaRepository<Participant,Integer> {

        public List<Participant> findAllByEvent(Event event);

        public Integer countByEvent(Event event);

        public Integer countByEventAndPresenceIsTrue(Event event);

        @Query(value = "select avg(s) from (select count(p.id_Event) as s from Eve_Participants p group by p.id_Event)",nativeQuery = true)
        public double getAvgParticipant();
}
