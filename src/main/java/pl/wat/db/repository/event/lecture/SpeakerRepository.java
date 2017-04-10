package pl.wat.db.repository.event.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.lecture.Speaker;

/**
 * Created by K on 2017-04-10.
 */
public interface SpeakerRepository extends JpaRepository<Speaker,Integer> {
}
