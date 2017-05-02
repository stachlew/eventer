package pl.wat.db.domain.event.image;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Embeddable
@Entity
@Table(name = "EVE_Speakers_Img")
public class SpeakerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_SPEAKERS_IMG_SEQ")
    @SequenceGenerator(sequenceName = "EVE_SPEAKERS_IMG_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_SPEAKERS_IMG_SEQ")
    private int idImage;

    @NotNull
    private int idSpeaker;

    @Lob
    @Column(nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Blob image;

    public SpeakerImage() {
    }

    public SpeakerImage(int idSpeaker, Blob image) {
        this.idSpeaker = idSpeaker;
        this.image = image;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
