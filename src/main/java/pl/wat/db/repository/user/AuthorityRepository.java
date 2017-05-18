package pl.wat.db.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.user.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
