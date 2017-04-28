package pl.wat.logic.event._model.dashboard;

import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;

import java.sql.Timestamp;

/**
 * Created by Wojciech on 2017-04-26.
 */
public class EventDashboardInfo {
    private int idEvent;
    private int capacity;
    private String description;
    private Timestamp endTime;
    private boolean freeEntrance;
    private boolean published;
    private boolean registerEnabled;
    private Timestamp startTime;
    private String title;
    private int visits;
    private String youtubeLink;

    // Place
    private Place place;

    // EventType
    private EventType eventType;

    public EventDashboardInfo() {
    }

    public EventDashboardInfo(Event event, Place place, EventType eventType) {
        this.idEvent = event.getIdEvent();
        this.capacity = event.getCapacity();
        this.description = event.getDescription();
        this.endTime = event.getEndTime();
        this.freeEntrance = event.isFreeEntrance();
        this.published = event.isPublished();
        this.registerEnabled = event.isRegisterEnabled();
        this.startTime = event.getStartTime();
        this.title = event.getTitle();
        this.visits = event.getVisits();
        this.youtubeLink = event.getYoutubeLink();

        this.place = place;
        this.eventType = eventType;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public boolean isFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(boolean freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
