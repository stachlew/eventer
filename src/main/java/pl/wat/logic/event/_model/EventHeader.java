package pl.wat.logic.event._model;

import pl.wat.db.domain.event.Event;

import java.sql.Timestamp;

public class EventHeader {

    private int idEvent;
    private String title;
    private String description;
    private Timestamp createDate;
    private Timestamp startDate;
    private Timestamp endDate;

    public EventHeader(int idEvent, String title, String description, Timestamp startDate, Timestamp endDate) {
        this.idEvent = idEvent;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventHeader(Event event) {
        this.idEvent=event.getIdEvent();
        this.title=event.getTitle();
        this.description=event.getDescription();
        this.createDate=event.getCreateDate();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
