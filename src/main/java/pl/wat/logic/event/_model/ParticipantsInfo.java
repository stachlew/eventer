package pl.wat.logic.event._model;

import pl.wat.db.domain.event.Participant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 2017-04-26.
 */
public class ParticipantsInfo {
    private int idEvent;
    private List<EventViewPartcipant> partcipants;

    public ParticipantsInfo() {
    }

    public ParticipantsInfo(List<Participant> partcipants, int idEvent) {
        this.partcipants = new LinkedList<>();
        for(Participant participant : partcipants) {
            this.partcipants.add(new EventViewPartcipant(participant));
        }

        this.idEvent = idEvent;
    }

    public List<EventViewPartcipant> getPartcipants() {
        return partcipants;
    }

    public void setPartcipants(List<EventViewPartcipant> partcipants) {
        this.partcipants = partcipants;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
}
