package pl.wat.db.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wat.db.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM (SELECT ua.USER_ID,COUNT(ua.USER_ID)" +
            " FROM USER_AUTHORITY ua group by ua.USER_ID HAVING COUNT(ua.USER_ID) < 2)" +
            " Temp INNER JOIN USERS u ON Temp.USER_ID = u.ID INNER JOIN USER_AUTHORITY ua ON ua.USER_ID = Temp.USER_ID" +
            " INNER JOIN AUTHORITY a ON a.ID = ua.AUTHORITY_ID WHERE u.ENABLED = 1 AND a.NAME != 'ROLE_ADMIN'", nativeQuery = true)
    long countUsersByEnabledIsTrueAndRoleTypeEqualsUser();
}