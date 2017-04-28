package pl.wat.logic.event._model;


import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Region;

import java.sql.Timestamp;

public class EventSearchForm {
    private String textContent;
    private Region region;
    private City city;
    private EventType eventType;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private boolean freeEntrance;
    private boolean registerEnabled;

    private String fromGeoWidth;
    private String toGeoWidth;
    private String fromGeoLenght;
    private String toGeoLenght;

    public EventSearchForm() {
    }

    public EventSearchForm(String textContent, Region region, City city, EventType eventType, Timestamp dateFrom, Timestamp dateTo, boolean freeEntrance, boolean registerEnabled, String fromGeoWidth, String toGeoWidth, String fromGeoLenght, String toGeoLenght) {
        this.textContent = textContent;
        this.region = region;
        this.city = city;
        this.eventType = eventType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.freeEntrance = freeEntrance;
        this.registerEnabled = registerEnabled;
        this.fromGeoWidth = fromGeoWidth;
        this.toGeoWidth = toGeoWidth;
        this.fromGeoLenght = fromGeoLenght;
        this.toGeoLenght = toGeoLenght;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
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

    public String getFromGeoWidth() {
        return fromGeoWidth;
    }

    public void setFromGeoWidth(String fromGeoWidth) {
        this.fromGeoWidth = fromGeoWidth;
    }

    public String getToGeoWidth() {
        return toGeoWidth;
    }

    public void setToGeoWidth(String toGeoWidth) {
        this.toGeoWidth = toGeoWidth;
    }

    public String getFromGeoLenght() {
        return fromGeoLenght;
    }

    public void setFromGeoLenght(String fromGeoLenght) {
        this.fromGeoLenght = fromGeoLenght;
    }

    public String getToGeoLenght() {
        return toGeoLenght;
    }

    public void setToGeoLenght(String toGeoLenght) {
        this.toGeoLenght = toGeoLenght;
    }
}
