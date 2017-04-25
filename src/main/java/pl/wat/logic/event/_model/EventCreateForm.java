package pl.wat.logic.event._model;

import java.sql.Timestamp;

/**
 * Created by michal on 19.04.2017.
 */
public class EventCreateForm {

    private int idEvent;
    private String title;
    private Timestamp startTime;
    private Timestamp endTime;

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

    private String geoLength;
    private String geoWidth;
}
