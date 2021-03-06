package pl.wat.db.domain.event;

import pl.wat.logic.event._model.view.EventViewOpinionForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "EVE_Opinions")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_OPINION_SEQ")
    @SequenceGenerator(sequenceName = "EVE_OPINION_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_OPINION_SEQ")
    private int idOpinion;

    @NotNull
    @Column(length = 500)
    @Size(min = 3, max = 500)
    private String content;

    @NotNull
    @Column(length = 50)
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    private Timestamp createDate;

    @NotNull
    private int rate;

    @NotNull
    @JoinColumn(name = "id_event")
    @ManyToOne
    private Event event;

    public Opinion() {
    }

    public Opinion(String content, String email, Timestamp createDate, Event event,int rate) {
        this.content = content;
        this.email = email;
        this.createDate = createDate;
        this.event = event;
        this.rate = rate;
    }

    public Opinion(EventViewOpinionForm form){
        if(form!=null){
            this.content=form.getContent();
            this.email=form.getEmail();
            this.createDate=form.getCreateDate();
            this.rate=form.getRate();
        }
    }

    public int getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(int idOpinion) {
        this.idOpinion = idOpinion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
