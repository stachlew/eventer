package pl.wat.logic.event._model.dashboard;

/**
 * Created by michal on 28.04.2017.
 */
public class EventDashboardAttendForm {
    private int idEvent;
    private int idParticipant;
    private boolean isPresent;

    public EventDashboardAttendForm() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
