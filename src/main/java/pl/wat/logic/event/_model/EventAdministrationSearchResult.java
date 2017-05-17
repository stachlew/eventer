package pl.wat.logic.event._model;

import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.user.User;

import java.sql.Timestamp;

public class EventAdministrationSearchResult {

    private int idEvent;
    private String title;
    private String cityname;
    private Timestamp startTime;
    private Timestamp endTime;
    private int capacity;
    private int visits;
    private SimpleUser user;
    private boolean published;
    private int registeredGuests;

    @Override
    public String toString() {
        return "EventAdministrationSearchResult{" +
                "idEvent=" + idEvent +
                ", title='" + title + '\'' +
                ", cityname='" + cityname + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", capacity=" + capacity +
                ", visits=" + visits +
                ", user=" + user +
                ", published=" + published +
                ", registeredGuests=" + registeredGuests +
                '}';
    }

    public EventAdministrationSearchResult() {
    }

    public EventAdministrationSearchResult(int idEvent, String title, String cityname, Timestamp startTime, Timestamp endTime, int capacity, int visits, SimpleUser user, boolean published, int registeredGuests) {
        this.idEvent = idEvent;
        this.title = title;
        this.cityname = cityname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.visits = visits;
        this.user = user;
        this.published = published;
        this.registeredGuests = registeredGuests;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
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

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getRegisteredGuests() {
        return registeredGuests;
    }

    public void setRegisteredGuests(int registeredGuests) {
        this.registeredGuests = registeredGuests;
    }
}
