package pl.wat.logic.model.Event;

import pl.wat.db.domain.event.Event;

public class EventHeader {

    private int idEvent;
    private String title;
    private String description;

    public EventHeader(Event event) {
        this.idEvent=event.getIdEvent();
        this.title=event.getTitle();
        this.description=event.getDescription();
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
}
