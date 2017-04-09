package pl.wat.db.domain.event.lecture;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Entity
@Table(name = "EVE_Speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_SPEAKER_SEQ")
    @SequenceGenerator(sequenceName = "EVE_SPEAKER_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_SPEAKER_SEQ")
    private int idSpeaker;

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

    @Column(length = 500)
    @NotNull
    @Size(min = 4, max = 500)
    private String description;

    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String phone;

    @Lob
    @Column(nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Blob image;

    public Speaker() {
    }

    public Speaker(String firstname, String lastname, String email, String description, String phone, Blob image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.description = description;
        this.phone = phone;
        this.image = image;
    }

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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
