package pl.wat.logic.event._model;

import pl.wat.db.domain.event.Event;

import java.sql.Timestamp;
import java.util.Date;

public class EventHeader {

    private int idEvent;
    private String title;
    private String description;
    private Timestamp createDate;

    public EventHeader(Event event) {
        this.idEvent=event.getIdEvent();
        this.title=event.getTitle();
        this.description=event.getDescription();
        this.createDate=event.getCreateDate();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
