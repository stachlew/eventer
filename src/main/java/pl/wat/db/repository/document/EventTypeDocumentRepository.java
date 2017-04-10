package pl.wat.db.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wat.db.domain.document.EventTypeDocument;

/**
 * Created by K on 2017-04-10.
 */
public interface EventTypeDocumentRepository extends JpaRepository<EventTypeDocument,Integer> {

}
