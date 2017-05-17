package pl.wat.logic.user.search;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.db.repository.user.UserSearchRepository;
import pl.wat.logic.user._model.UserAdministrationSearchForm;
import pl.wat.logic.user._model.UserAdministrationSearchResult;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserSearchService {

    static final int sizeOfPage =5;

    @Autowired
    UserSearchRepository userSearchRepository;

    @Autowired
    UserExpression userExpression;

    public List<UserAdministrationSearchResult> findAdministrationUserPage(UserAdministrationSearchForm form){
        Predicate predicate = userExpression.createPredicateDependsOfUserSearchForm(form);
        Iterable<User> userList = userSearchRepository.findAll(predicate, new PageRequest(form.getSiteNo(),sizeOfPage));
        List<UserAdministrationSearchResult> resultList = new LinkedList<>();

        for (User u:userList){
            UserAdministrationSearchResult userResult = new UserAdministrationSearchResult(
                u.getId(),
                    u.getUsername(),
                    u.getFirstname(),
                    u.getLastname(),
                    u.getEmail(),
                    u.getPhone(),
                    u.getEnabled(),
                    u.getLastpassres()
            );
            resultList.add(userResult);
        }
        return resultList;
    }

    public List<UserAdministrationSearchResult> findAdministrationUserFull(UserAdministrationSearchForm form){
        return null;
    }

}
