package pl.wat.db.domain.event.image;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Embeddable
@Entity
@Table(name = "EVE_Events_Img")
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_SPEAKERS_IMG_SEQ")
    @SequenceGenerator(sequenceName = "EVE_SPEAKERS_IMG_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_SPEAKERS_IMG_SEQ")
    private int idImage;

    @NotNull
    private int idEvent;

    @Lob
    @Column(nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Blob image;

    public EventImage() {
    }

    public EventImage(int idEvent, Blob image) {
        this.idEvent = idEvent;
        this.image = image;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
