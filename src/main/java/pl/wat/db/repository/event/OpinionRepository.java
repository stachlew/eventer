package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.Opinion;

/**
 * Created by K on 2017-04-10.
 */
public interface OpinionRepository extends JpaRepository<Opinion,Integer> {
}
