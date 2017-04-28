package pl.wat.logic.event._model.view;


import pl.wat.db.domain.event.Opinion;

import java.sql.Timestamp;

public class EventViewOpinion {

    private int idOpinion;
    private int idEvent;
    private String content;
    private String email;
    private Timestamp createDate;
    private int rate;

    public EventViewOpinion() {
    }

    public EventViewOpinion(Opinion opinion) {
        this.idOpinion=opinion.getIdOpinion();
        this.idEvent=opinion.getEvent().getIdEvent();
        this.content=opinion.getContent();
        this.email=opinion.getEmail();
        this.createDate=opinion.getCreateDate();
        this.rate=opinion.getRate();
    }

    public EventViewOpinion(int idOpinion, int idEvent, String content, String email, Timestamp createDate,int rate) {
        this.idOpinion = idOpinion;
        this.idEvent = idEvent;
        this.content = content;
        this.email = email;
        this.createDate = createDate;
        this.rate=rate;
    }

    public int getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(int idOpinion) {
        this.idOpinion = idOpinion;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
