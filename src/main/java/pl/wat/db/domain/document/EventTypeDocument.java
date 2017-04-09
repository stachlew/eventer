package pl.wat.db.domain.document;

import pl.wat.db.domain.event.EventType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EVE_EVENT_TYPE_DOCUMENTS")
public class EventTypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_EVENT_TYPE_DOCUMENT_SEQ")
    @SequenceGenerator(sequenceName = "EVE_EVENT_TYPE_DOCUMENT_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_EVENT_TYPE_DOCUMENT_SEQ")
    private int idEventTypeDocument;

    @NotNull
    @JoinColumn(name = "id_event_type")
    @ManyToOne
    private EventType eventType;

    @NotNull
    @JoinColumn(name = "id_document")
    @ManyToOne
    private Document document;

    public EventTypeDocument() {
    }

    public EventTypeDocument(EventType eventType, Document document) {
        this.eventType = eventType;
        this.document = document;
    }

    public int getIdEventTypeDocument() {
        return idEventTypeDocument;
    }

    public void setIdEventTypeDocument(int idEventTypeDocument) {
        this.idEventTypeDocument = idEventTypeDocument;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
