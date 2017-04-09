package pl.wat.db.domain.event;

import org.hibernate.annotations.ColumnDefault;
import pl.wat.db.domain.event.Event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_PARTICIPANT_SEQ")
    @SequenceGenerator(sequenceName = "EVE_PARTICIPANT_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_PARTICIPANT_SEQ")
    private int idParticipant;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String firstname;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;

    @Column(length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @NotNull
    @ColumnDefault(value = "0")
    private boolean activated;

    @NotNull
    @ColumnDefault(value = "0")
    private boolean presence;

    @NotNull
    @JoinColumn(name = "id_event")
    @ManyToOne
    private Event event;

    public Participant() {
    }

    public Participant(String firstname, String lastname, String email, boolean activated, boolean presence, Event event) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.activated = activated;
        this.presence = presence;
        this.event = event;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
