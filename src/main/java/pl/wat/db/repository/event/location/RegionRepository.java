package pl.wat.db.repository.event.location;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.location.Region;

/**
 * Created by K on 2017-04-10.
 */
public interface RegionRepository extends JpaRepository<Region,Integer> {
}
