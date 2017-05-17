package pl.wat.logic.event.search;


import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.*;
import pl.wat.db.repository.event.EventSearchRepository;
import pl.wat.logic.event._model.EventAdministrationSearchForm;
import pl.wat.logic.event._model.EventSearchForm;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by K on 2017-04-26.
 */
@Service
public class EventExpressions {
    @Autowired
    EventSearchRepository eventSearchRepository;

    public Predicate createPredicateDependsOfEventSearchForm(EventSearchForm searchForm){

        BooleanExpression booleanExpression=QEvent.event.published.isTrue();

        if (searchForm.getTextContent()
        !=null){
            booleanExpression=booleanExpression.and((QEvent.event.title.upper().like("%"+searchForm.getTextContent().toUpperCase()+"%")).or(QEvent.event.description.upper().like("%"+searchForm.getTextContent().toUpperCase()+"%")));
        }
        if(searchForm.getCity()!=null){
            booleanExpression=booleanExpression.and(QEvent.event.place.city.eq(searchForm.getCity()));
        }
        if(searchForm.getRegion()!=null){
            booleanExpression=booleanExpression.and(QEvent.event.place.city.region.eq(searchForm.getRegion()));
        }
        if (searchForm.getEventType()!=null){
            booleanExpression=booleanExpression.and(QEvent.event.eventType.eq(searchForm.getEventType()));
        }
        if(searchForm.getDateFrom()!=null){
            booleanExpression=booleanExpression.and(QEvent.event.startTime.gt(searchForm.getDateFrom()));
        }
        if(searchForm.getDateTo()!=null){
            booleanExpression=booleanExpression.and(QEvent.event.startTime.lt(searchForm.getDateTo()));
        }
        if(searchForm.isFreeEntrance()){
            booleanExpression=booleanExpression.and(QEvent.event.freeEntrance.isTrue());
        }
        if(searchForm.isRegisterEnabled()){
            booleanExpression=booleanExpression.and(QEvent.event.registerEnabled.isTrue());
        }
        if(searchForm.getFromGeoLenght()!=null){
            List<BigDecimal> idEventByGeoLocation = eventSearchRepository.findIdEventByGeoLocation(searchForm.getFromGeoLenght(), searchForm.getToGeoLenght(), searchForm.getFromGeoWidth(), searchForm.getToGeoWidth());
            List<Integer> idEvents = new LinkedList<>();
            for (BigDecimal i:idEventByGeoLocation
                 ) {
                idEvents.add(i.intValue());
            }
            if(!idEvents.isEmpty()) {
                booleanExpression = booleanExpression.and(QEvent.event.idEvent.notIn(idEvents));
            }
        }


        return booleanExpression ;
    }

    public Predicate createPredicateDependsOfEventSearchForm(EventAdministrationSearchForm searchForm){

        BooleanExpression booleanExpression=QEvent.event.idEvent.isNotNull();

        if (searchForm.getTextContent()!=null){
            booleanExpression=booleanExpression.and((QEvent.event.title.upper().like("%"+searchForm.getTextContent().toUpperCase()+"%"))
                    .or(QEvent.event.user.username.upper().like("%"+searchForm.getTextContent().toUpperCase()+"%")));
        }

        return booleanExpression ;
    }

    public static Predicate hasCapasity(int capacity){
        return QEvent.event.capacity.lt(300);
    }

}
