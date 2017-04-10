package pl.wat.db.domain.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Event_Types")
public class EventType {

    @Id
    private int idEventType;

    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String eventTypeName;

    public EventType() {
    }

    public EventType(int idEventType, String eventTypeName) {
        this.idEventType = idEventType;
        this.eventTypeName = eventTypeName;
    }

    public int getIdEventType() {
        return idEventType;
    }

    public void setIdEventType(int idEventType) {
        this.idEventType = idEventType;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
}
