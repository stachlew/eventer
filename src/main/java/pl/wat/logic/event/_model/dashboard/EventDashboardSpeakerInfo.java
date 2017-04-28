package pl.wat.logic.event._model.dashboard;

import pl.wat.db.domain.event.lecture.Speaker;

/**
 * Created by Wojciech on 2017-04-28.
 */
public class EventDashboardSpeakerInfo {
    private int idSpeaker;
    private String description;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public EventDashboardSpeakerInfo() {
    }

    public EventDashboardSpeakerInfo(Speaker speaker) {
        this.idSpeaker = speaker.getIdSpeaker();
        this.description = speaker.getDescription();
        this.email = speaker.getEmail();
        this.firstName = speaker.getFirstname();
        this.lastName = speaker.getLastname();
        this.phone = speaker.getPhone();
    }

    public int getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
