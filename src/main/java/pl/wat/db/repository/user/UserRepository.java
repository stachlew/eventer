package pl.wat.db.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}