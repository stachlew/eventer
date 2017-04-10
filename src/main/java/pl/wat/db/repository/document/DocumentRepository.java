package pl.wat.db.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.document.Document;

/**
 * Created by K on 2017-04-10.
 */
public interface DocumentRepository extends JpaRepository<Document,Integer> {

}
