package pl.wat.api.event.search;


import com.querydsl.core.types.Predicate;
import pl.wat.db.domain.event.QEvent;


/**
 * Created by K on 2017-04-26.
 */
public class EventExpressions {
    public static Predicate hasTitle(String title){
        return QEvent.event.title.eq(title);
    }

}
