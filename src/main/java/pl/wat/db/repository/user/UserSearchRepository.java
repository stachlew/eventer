package pl.wat.db.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import pl.wat.db.domain.user.User;



public interface UserSearchRepository extends JpaRepository<User,Integer>,QueryDslPredicateExecutor<User> {
}
