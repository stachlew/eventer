package pl.wat.logic.event._model;


import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.domain.user.User;

import java.sql.Timestamp;

public class EventSearchResult {

    private int idEvent;
    private SimpleUser user;

    private String title;
    private String description;

    private int capacity;
    private int registeredGuests;

    private Timestamp createDate;
    private Timestamp startTime;
    private Timestamp endTime;

    private boolean freeEntrance;
    private boolean registerEnabled;

    private int visits;
    private String youtubeLink;

    // Place
    private String geoLenght;
    private String geoWidth;
    private String streetName;
    private String streetNo;

    // EventType
    private String eventType;

    // City
    private String cityName;

    // Region
    private String regionName;

    public EventSearchResult() {
    }

    public EventSearchResult(Event event, User user, Place place, EventType eventType, City city, Region region,int registeredGuests) {
        this.idEvent = event.getIdEvent();
        this.user = new SimpleUser(user.getId(),user.getUsername());

        this.title = event.getTitle();
        this.description = event.getDescription();

        this.capacity = event.getCapacity();
        this.registeredGuests=registeredGuests;

        this.createDate = event.getCreateDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();

        this.freeEntrance = event.isFreeEntrance();
        this.registerEnabled = event.isRegisterEnabled();

        this.visits = event.getVisits();
        this.youtubeLink = event.getYoutubeLink();

        this.geoLenght = place.getGeoLength();
        this.geoWidth = place.getGeoWidth();
        this.streetName = place.getStreetName();
        this.streetNo = place.getStreetNo();

        this.eventType = eventType.getEventTypeName();

        this.cityName = city.getCityName();

        this.regionName = region.getRegionName();
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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

    public boolean isFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(boolean freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public boolean isRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
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

    public String getGeoLenght() {
        return geoLenght;
    }

    public void setGeoLenght(String geoLenght) {
        this.geoLenght = geoLenght;
    }

    public String getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(String geoWidth) {
        this.geoWidth = geoWidth;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegisteredGuests() {
        return registeredGuests;
    }

    public void setRegisteredGuests(int registeredGuests) {
        this.registeredGuests = registeredGuests;
    }
}
