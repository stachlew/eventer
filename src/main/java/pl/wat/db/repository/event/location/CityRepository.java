package pl.wat.db.repository.event.location;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Region;

import java.util.List;

/**
 * Created by K on 2017-04-10.
 */
public interface CityRepository extends JpaRepository<City,Integer> {
    City findByCityName(String cityName);

    List<City> findAllByRegion(Region region);

}
