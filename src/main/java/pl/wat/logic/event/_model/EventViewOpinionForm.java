package pl.wat.logic.event._model;


import java.sql.Timestamp;

public class EventViewOpinionForm {

    private int idEvent;
    private String content;
    private String email;
    private Timestamp createDate;

    public EventViewOpinionForm() {
    }

    public EventViewOpinionForm(int idEvent, String content, String email, Timestamp createDate) {
        this.idEvent = idEvent;
        this.content = content;
        this.email = email;
        this.createDate = createDate;
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
}
