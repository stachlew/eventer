package pl.wat.logic.event._model.view;


import java.sql.Timestamp;

public class EventViewOpinionForm {

    private int idEvent;
    private String content;
    private String email;
    private Timestamp createDate;
    private int rate;

    public EventViewOpinionForm() {
    }

    public EventViewOpinionForm(int idEvent, String content, String email, Timestamp createDate,int rate) {
        this.idEvent = idEvent;
        this.content = content;
        this.email = email;
        this.createDate = createDate;
        this.rate =rate;
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
