package pl.wat.db.repository.event.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Speaker;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface SpeakerRepository extends JpaRepository<Speaker,Integer> {

    @Query ("select distinct s.idSpeaker from Event e,Lecture l, Speaker s where e.idEvent=l.event.idEvent and l.speaker.idSpeaker=s.idSpeaker and e.idEvent=(:idEvent)")
    public List<Integer> getDistinctIdSpeakersByIdEvent(@Param("idEvent")int idEvent);

}
