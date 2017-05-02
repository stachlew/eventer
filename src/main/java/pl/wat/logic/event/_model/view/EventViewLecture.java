package pl.wat.logic.event._model.view;

import pl.wat.db.domain.event.lecture.Lecture;

import java.sql.Timestamp;

public class EventViewLecture {

    private int idLecture;
    private int idEvent;

    private String lectureName;
    private String description;

    private EventViewSpeaker eventViewSpeaker;

    private Timestamp startTime;
    private Timestamp endTime;

    public EventViewLecture(Lecture lecture){
        this.idLecture = lecture.getIdLecture();
        this.idEvent = lecture.getEvent().getIdEvent();

        this.lectureName = lecture.getLectureName();
        this.description = lecture.getDescription();

        this.eventViewSpeaker = new EventViewSpeaker(lecture.getSpeaker());
        this.startTime = lecture.getStartTime();
        this.endTime = lecture.getEndTime();
    }

    public EventViewLecture() {
    }

    public int getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventViewSpeaker getEventViewSpeaker() {
        return eventViewSpeaker;
    }

    public void setEventViewSpeaker(EventViewSpeaker eventViewSpeaker) {
        this.eventViewSpeaker = eventViewSpeaker;
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
}
