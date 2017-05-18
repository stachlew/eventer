package pl.wat.logic.user.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.QAuthority;
import pl.wat.db.domain.user.QUser;
import pl.wat.db.repository.user.AuthorityRepository;
import pl.wat.logic.user._model.UserAdministrationSearchForm;
import com.querydsl.core.types.Predicate;


@Service
public class UserExpression {

    @Autowired
    AuthorityRepository authorityRepository;

    public Predicate createPredicateDependsOfUserSearchForm(UserAdministrationSearchForm form){
        BooleanExpression booleanExpression= QUser.user.username.isNotNull();
        booleanExpression=booleanExpression.and(QUser.user.authorities.contains(authorityRepository.getOne(2)).not());

        if (form.getTextContent()!=null){
             booleanExpression = booleanExpression.and((QUser.user.username.upper().like("%"+form.getTextContent().toUpperCase()+"%"))
                    .or(QUser.user.firstname.upper().like("%"+form.getTextContent().toUpperCase()+"%"))
                    .or(QUser.user.lastname.upper().like("%"+form.getTextContent().toUpperCase()+"%")));
        }
        return booleanExpression;
    }
}
