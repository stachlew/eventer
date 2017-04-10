package pl.wat.db.domain.event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.sql.Timestamp;
import org.hibernate.annotations.ColumnDefault;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.user.User;

@Entity
@Table(name = "EVE_Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_EVENT_SEQ")
    @SequenceGenerator(sequenceName = "EVE_EVENT_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_EVENT_SEQ")
    private int idEvent;

    @Column(length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String title;

    @Column(length = 1000)
    @NotNull
    @Size(min = 4, max = 1000)
    private String description;

    @NotNull
    @JoinColumn(name = "id_place")
    @ManyToOne
    private Place place;

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    private int capacity;

    @NotNull
    @ColumnDefault(value = "0")
    private int visits;

    @Lob
    @Column(nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Blob image;


    @NotNull
    @ColumnDefault(value = "0")
    private boolean deleted;

    @NotNull
    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    @NotNull
    @JoinColumn(name = "id_event_type")
    @ManyToOne
    private EventType eventType;

    @NotNull
    @JoinColumn(name = "id_template")
    @ManyToOne
    private Template template;

    public Event() {
    }

    public Event(String title, String description, Place place, Timestamp startTime, Timestamp endTime, int capacity, int visits, Blob image, boolean deleted, User user, EventType eventType, Template template) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.visits = visits;
        this.image = image;
        this.deleted = deleted;
        this.user = user;
        this.eventType = eventType;
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
