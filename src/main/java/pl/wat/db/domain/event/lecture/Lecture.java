package pl.wat.db.domain.event.lecture;

import pl.wat.db.domain.event.Event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "EVE_Lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_LECTURE_SEQ")
    @SequenceGenerator(sequenceName = "EVE_LECTURE_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_LECTURE_SEQ")
    private int idLecture;

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    @NotNull
    @Column(length = 50)
    @Size(min = 3, max = 50)
    private String lectureName;

    @Column(length = 500)
    @Size(min = 3, max = 500)
    private String description;

    @NotNull
    @JoinColumn(name = "id_speaker")
    @ManyToOne
    private Speaker speaker;

    @NotNull
    @JoinColumn(name = "id_event")
    @ManyToOne
    private Event event;

    public Lecture() {
    }

    public Lecture(Timestamp startTime, Timestamp endTime, String lectureName, String description, Speaker speaker, Event event) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lectureName = lectureName;
        this.description = description;
        this.speaker = speaker;
        this.event = event;
    }

    public int getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(int idLecture) {
        this.idLecture = idLecture;
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

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
