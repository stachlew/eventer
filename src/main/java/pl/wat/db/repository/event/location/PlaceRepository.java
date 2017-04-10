package pl.wat.db.repository.event.location;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.location.Place;

/**
 * Created by K on 2017-04-10.
 */
public interface PlaceRepository extends JpaRepository<Place,Integer> {
}
