package pl.wat.db.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.event.Template;

/**
 * Created by K on 2017-04-10.
 */
public interface TemplateRepository extends JpaRepository<Template,Integer> {
}
