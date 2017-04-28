package pl.wat.logic.event._model.view;

import pl.wat.db.domain.event.Participant;

/**
 * Created by Wojciech on 2017-04-26.
 */
public class EventViewPartcipant {
    // Participants
    private int idPartcipant;
    private boolean activated;
    private String email;
    private String firstName;
    private String lastName;
    private boolean presence;

    public EventViewPartcipant() {
    }

    public EventViewPartcipant(Participant participant) {
        this.idPartcipant = participant.getIdParticipant();
        this.activated = participant.isActivated();
        this.email = participant.getEmail();
        this.firstName = participant.getFirstname();
        this.lastName = participant.getLastname();
        this.presence = participant.isPresence();
    }

    public int getIdPartcipant() {
        return idPartcipant;
    }

    public void setIdPartcipant(int idPartcipant) {
        this.idPartcipant = idPartcipant;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }
}
