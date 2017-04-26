package pl.wat.db.repository.event.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;

import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface LectureRepository extends JpaRepository<Lecture,Integer> {
    public List<Lecture> getAllByEventOrderByStartTime(Event event);

    public List<Lecture> findAllByEvent(Event event);
}
