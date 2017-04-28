package pl.wat.logic.event._model.view;


import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.lecture.Lecture;
import pl.wat.db.domain.event.lecture.Speaker;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class EventViewDetails {

    private int idEvent;
    private String eventTypeName;
    private String username;

    private String title;
    private String description;

    private String regionName;
    private String cityName;
    private String streetName;
    private String streetNo;
    private String geoLength;
    private String geoWidth;

    private String youtubeLink;

    private boolean published;
    private boolean freeEntrance;
    private boolean registerEnabled;

    private int capacity;
    private int visits;

    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createDate;

    private List<EventViewLecture> lectures;

    private List<EventViewSpeaker> speakers;

    public EventViewDetails(){
    }

    public EventViewDetails(Event event, List<Lecture> lectures,List<Speaker> speakers){
        this.idEvent=event.getIdEvent();
        this.eventTypeName=event.getEventType().getEventTypeName();
        this.username=event.getUser().getUsername();

        this.title=event.getTitle();
        this.description=event.getDescription();

        this.regionName=event.getPlace().getCity().getRegion().getRegionName();
        this.cityName=event.getPlace().getCity().getCityName();
        this.streetName=event.getPlace().getStreetName();
        this.streetNo=event.getPlace().getStreetNo();
        this.geoLength=event.getPlace().getGeoLength();
        this.geoWidth=event.getPlace().getGeoWidth();

        this.youtubeLink=event.getYoutubeLink();

        this.published=event.isPublished();
        this.freeEntrance=event.isFreeEntrance();
        this.registerEnabled=event.isRegisterEnabled();

        this.capacity=event.getCapacity();
        this.visits=event.getCapacity();

        this.startTime=event.getStartTime();
        this.endTime=event.getEndTime();
//        this.createDate=event.getCreateDate();

        this.lectures = new LinkedList<>();
        for (Lecture lecture: lectures) {
            this.lectures.add(new EventViewLecture(lecture));
        }

        this.speakers = new LinkedList<>();
        for (Speaker speaker: speakers) {
            this.speakers.add(new EventViewSpeaker(speaker));
        }
    }

    public List<EventViewSpeaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<EventViewSpeaker> speakers) {
        this.speakers = speakers;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getGeoLength() {
        return geoLength;
    }

    public void setGeoLength(String geoLength) {
        this.geoLength = geoLength;
    }

    public String getGeoWidth() {
        return geoWidth;
    }

    public void setGeoWidth(String geoWidth) {
        this.geoWidth = geoWidth;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<EventViewLecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<EventViewLecture> lectures) {
        this.lectures = lectures;
    }
}
