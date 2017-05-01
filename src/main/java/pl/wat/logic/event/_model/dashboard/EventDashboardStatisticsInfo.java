package pl.wat.logic.event._model.dashboard;

/**
 * Created by michal on 28.04.2017.
 */
public class EventDashboardStatisticsInfo {
    private int visits;
    private int participants;
    private int presence;
    private int stars5;
    private int stars4;
    private int stars3;
    private int stars2;
    private int stars1;

    public EventDashboardStatisticsInfo() {
    }

    public EventDashboardStatisticsInfo(int visits, int participants, int presence) {
        this.visits = visits;
        this.participants = participants;
        this.presence = presence;
    }

    public EventDashboardStatisticsInfo(int visits, int participants, int presence, int stars5, int stars4, int stars3, int stars2, int stars1) {
        this.visits = visits;
        this.participants = participants;
        this.presence = presence;
        this.stars5 = stars5;
        this.stars4 = stars4;
        this.stars3 = stars3;
        this.stars2 = stars2;
        this.stars1 = stars1;
    }

    public int getStars5() {
        return stars5;
    }

    public void setStars5(int stars5) {
        this.stars5 = stars5;
    }

    public int getStars4() {
        return stars4;
    }

    public void setStars4(int stars4) {
        this.stars4 = stars4;
    }

    public int getStars3() {
        return stars3;
    }

    public void setStars3(int stars3) {
        this.stars3 = stars3;
    }

    public int getStars2() {
        return stars2;
    }

    public void setStars2(int stars2) {
        this.stars2 = stars2;
    }

    public int getStars1() {
        return stars1;
    }

    public void setStars1(int stars1) {
        this.stars1 = stars1;
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
