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

    public EventDashboardInfo() {
    }

    public EventDashboardInfo(Event event, Place place, EventType eventType, City city, Region region) {
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
}
