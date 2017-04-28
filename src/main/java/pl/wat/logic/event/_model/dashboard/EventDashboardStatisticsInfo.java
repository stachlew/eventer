package pl.wat.logic.event._model.dashboard;

/**
 * Created by michal on 28.04.2017.
 */
public class EventDashboardStatisticsInfo {
    private int visits;
    private int participants;
    private int presence;

    public EventDashboardStatisticsInfo() {
    }

    public EventDashboardStatisticsInfo(int visits, int participants, int presence) {
        this.visits = visits;
        this.participants = participants;
        this.presence = presence;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }
}
