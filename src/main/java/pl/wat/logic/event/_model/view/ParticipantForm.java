package pl.wat.logic.event._model.view;

/**
 * Created by K on 2017-04-21.
 */
public class ParticipantForm {
    private String firstname;
    private String lastname;
    private String email;
    private int idEvent;

    public ParticipantForm() {
    }

    public ParticipantForm(String firstname, String lastname, String email, int idEvent) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.idEvent = idEvent;
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

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
}
