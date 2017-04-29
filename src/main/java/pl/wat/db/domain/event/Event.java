package pl.wat.db.domain.event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.sql.Timestamp;


import com.querydsl.core.annotations.*;
import org.hibernate.annotations.ColumnDefault;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.user.User;

@Embeddable
@Entity
@Table(name = "EVE_Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_EVENT_SEQ")
    @SequenceGenerator(sequenceName = "EVE_EVENT_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_EVENT_SEQ")
    private int idEvent;

    @QueryType(PropertyType.STRING)
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
    @QueryInit("city.*")
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





    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    @NotNull
    @JoinColumn(name = "id_event_type")
    @ManyToOne
    private EventType eventType;


    public Event() {
    }

    public Event(String title, String description, Place place, Timestamp startTime, Timestamp endTime, Timestamp createDate, int capacity, int visits, Blob image, User user, EventType eventType) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createDate = createDate;
        this.capacity = capacity;
        this.visits = visits;
        this.image = image;

        this.user = user;
        this.eventType = eventType;
    }

    public Event(EventBuilder eventBuilder) {
        this.idEvent = eventBuilder.idEvent;
        this.title = eventBuilder.title;
        this.description = eventBuilder.description;
        this.place = eventBuilder.place;
        this.startTime = eventBuilder.startTime;
        this.endTime = eventBuilder.endTime;
        this.createDate = eventBuilder.createDate;
        this.capacity = eventBuilder.capacity;
        this.visits = eventBuilder.visits;
        this.image = eventBuilder.image;
        this.user = eventBuilder.user;
        this.eventType = eventBuilder.eventType;
        this.youtubeLink = eventBuilder.youtubeLink;
        this.published = eventBuilder.published;
        this.freeEntrance = eventBuilder.freeEntrance;
        this.registerEnabled = eventBuilder.registerEnabled;
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

    public void setStartTime(String Timestamp) {
        this.startTime = startTime;
    }

    public void setStartTime(Timestamp timestamp) {
        this.startTime = timestamp;
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


    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public static class EventBuilder {
        private int idEvent;
        private String title;
        private String description;
        private Place place;
        private Timestamp startTime;
        private Timestamp endTime;
        private Timestamp createDate;
        private int capacity;
        private int visits;
        private Blob image;
        private User user;
        private EventType eventType;
        private String youtubeLink;
        private boolean published;
        private boolean freeEntrance;
        private boolean registerEnabled;

        public EventBuilder id(int idEvent) {
            this.idEvent = idEvent;
            return this;
        }

        public EventBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EventBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EventBuilder place(Place place) {
            this.place = place;
            return this;
        }

        public EventBuilder startTime(Timestamp startTime) {
            this.startTime = startTime;
            return this;
        }

        public EventBuilder endTime(Timestamp endTime) {
            this.endTime = endTime;
            return this;
        }

        public EventBuilder createDate(Timestamp createDate) {
            this.createDate = createDate;
            return this;
        }

        public EventBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public EventBuilder visits(int visits) {
            this.visits = visits;
            return this;
        }

        public EventBuilder image(Blob image) {
            this.image = image;
            return this;
        }



        public EventBuilder user(User user) {
            this.user = user;
            return this;
        }

        public EventBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }


        public EventBuilder youtubeLink(String youtubeLink) {
            this.youtubeLink = youtubeLink;
            return this;
        }

        public EventBuilder published(boolean published) {
            this.published = published;
            return this;
        }

        public EventBuilder freeEntrance(boolean freeEntrance) {
            this.freeEntrance = freeEntrance;
            return this;
        }

        public EventBuilder registerEnabled(boolean registerEnabled) {
            this.registerEnabled = registerEnabled;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", place=" + place +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                //", createDate='" + createDate + '\'' +
                ", capacity=" + capacity +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", published=" + published +
                ", freeEntrance=" + freeEntrance +
                ", registerEnabled=" + registerEnabled +
                ", visits=" + visits +
                ", image=" + image +
                ", user=" + user +
                ", eventType=" + eventType +
                '}';
    }
}
