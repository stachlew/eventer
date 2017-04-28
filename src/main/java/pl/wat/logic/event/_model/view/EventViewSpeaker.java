package pl.wat.logic.event._model.view;

import pl.wat.db.domain.event.lecture.Speaker;

public class EventViewSpeaker {

    private int idSpeaker;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private String phone;

    public EventViewSpeaker(Speaker speaker){
        this.idSpeaker = speaker.getIdSpeaker();
        this.firstname=speaker.getFirstname();
        this.lastname=speaker.getLastname();
        this.email=speaker.getEmail();
        this.description=speaker.getDescription();
        this.phone=speaker.getPhone();
    }

    public EventViewSpeaker(){}

    public int getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
