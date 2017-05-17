package pl.wat.logic.event._model;

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
    private boolean register;
    private int registeredGuests;

    public EventAdministrationSearchResult() {
    }

    public EventAdministrationSearchResult(int idEvent, String title, String cityname, Timestamp startTime, Timestamp endTime, int capacity, int visits, SimpleUser user, boolean published, boolean register, int registeredGuests) {
        this.idEvent = idEvent;
        this.title = title;
        this.cityname = cityname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.visits = visits;
        this.user = user;
        this.published = published;
        this.register = register;
        this.registeredGuests = registeredGuests;
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

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public int getRegisteredGuests() {
        return registeredGuests;
    }

    public void setRegisteredGuests(int registeredGuests) {
        this.registeredGuests = registeredGuests;
    }
}
