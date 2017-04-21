package pl.wat.db.domain.event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import pl.wat.db.domain.event.lecture.Lecture;
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

    @NotNull
    private Timestamp createDate;

    private int capacity;

    @Column(length = 100)
    @Size(max = 100)
    private String youtubeLink;

    @NotNull
    @ColumnDefault(value = "0")
    private boolean published;

    @ColumnDefault(value = "1")
    private boolean freeEntrance;

    @NotNull
    @ColumnDefault(value = "1")
    private boolean registerEnabled;

    @NotNull
    @ColumnDefault(value = "0")
    private int visits;

    @Lob
    @Column(nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Blob image;


    @Column(length = 50)
    @NotNull
    //  @ColumnDefault(value = "UNPUBLISHED")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;


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

    public Event(String title, String description, Place place, Timestamp startTime, Timestamp endTime, Timestamp createDate, int capacity, int visits, Blob image, EventStatus eventStatus, User user, EventType eventType, Template template) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createDate = createDate;
        this.capacity = capacity;
        this.visits = visits;
        this.image = image;
        this.eventStatus = eventStatus;
        this.user = user;
        this.eventType = eventType;
        this.template = template;
    }

    public Event(String title, String description, Place place, Timestamp startTime, Timestamp endTime, Timestamp createDate, int capacity, String youtubeLink, boolean published, boolean freeEntrance, boolean registerEnabled, int visits, Blob image, EventStatus eventStatus, User user, EventType eventType, Template template) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createDate = createDate;
        this.capacity = capacity;
        this.youtubeLink = youtubeLink;
        this.published = published;
        this.freeEntrance = freeEntrance;
        this.registerEnabled = registerEnabled;
        this.visits = visits;
        this.image = image;
        this.eventStatus = eventStatus;
        this.user = user;
        this.eventType = eventType;
        this.template = template;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(boolean freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public boolean isRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
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

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

}
