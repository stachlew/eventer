package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import pl.wat.db.domain.event.Event;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by K on 2017-04-25.
 */
public interface EventSearchRepository extends JpaRepository<Event,Integer>,QueryDslPredicateExecutor<Event> {

    @Query(value = "select e.ID_EVENT\n" +
            "from EVE_PLACES p,EVE_EVENTS e\n" +
            "where (TO_NUMBER(GEO_LENGTH,'999.9999999999')<TO_NUMBER(:GEO_LENTGTH_FROM,'999.9999999999') or TO_NUMBER(GEO_LENGTH,'999.9999999999')>TO_NUMBER(:GEO_LENTGTH_TO,'999.9999999999') \n" +
            "or TO_NUMBER(geo_WIDTH,'999.9999999999')<TO_NUMBER(:GEO_WIDTH_FROM,'999.9999999999') or TO_NUMBER(geo_WIDTH,'999.9999999999')>TO_NUMBER(:GEO_WIDTH_TO,'999.9999999999') \n" +
            ")and p.id_place=e.id_place",nativeQuery = true)
    List<BigDecimal> findIdEventByGeoLocation(@Param("GEO_LENTGTH_FROM")String geoLengthFrom, @Param("GEO_LENTGTH_TO")String geoLengthTo
    , @Param("GEO_WIDTH_FROM")String geoWidthFrom, @Param("GEO_WIDTH_TO")String geoWidthTo);

}
