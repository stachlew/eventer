package pl.wat.logic.event.search;


import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.core.EntityMetadata;
import org.springframework.stereotype.Controller;
import pl.wat.db.domain.event.QEvent;
import pl.wat.logic.event._model.EventSearchForm;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;


/**
 * Created by K on 2017-04-26.
 */
public class EventExpressions {

    public static Predicate createPredicateDependsOfEventSearchForm(EventSearchForm searchForm){

        BooleanExpression booleanExpression=QEvent.event.idEvent.isNotNull();

        if (searchForm.getTextContent()!=null){
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
            booleanExpression=booleanExpression.and(QEvent.event.endTime.lt(searchForm.getDateTo()));
        }
        if(searchForm.isFreeEntrance()){
            booleanExpression=booleanExpression.and(QEvent.event.freeEntrance.isTrue());
        }
        if(searchForm.isRegisterEnabled()){
            booleanExpression=booleanExpression.and(QEvent.event.registerEnabled.isTrue());
        }
//        if(searchForm.getFromGeoLenght()!=null){
//            booleanExpression=booleanExpression.and(QEvent.event.place.geoLength.castToNum(Double.class).gt(Double.parseDouble(searchForm.getFromGeoLenght())));
//        }
//        if(searchForm.getToGeoLenght()!=null){
//            booleanExpression=booleanExpression.and(QEvent.event.place.geoLength.castToNum(BigDecimal.class).lt(Double.parseDouble(searchForm.getToGeoLenght())));
//        }


        return booleanExpression ;
    }


    public static Predicate hasCapasity(int capacity){
        return QEvent.event.capacity.lt(300);
    }

}
