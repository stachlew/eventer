package pl.wat.logic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;

import java.sql.Timestamp;

/**
 * Created by K on 2017-05-10.
 */
@Service
public class StatisticService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;


    public long getCountEvent(){
        return eventRepository.count();
    }

    public long getCountParticipant(){
        return participantRepository.count();
    }

    public long getCountVisits(){
        return eventRepository.getCountVisits();
    }

    public double getAvgVisits(){
        return eventRepository.getAvgVisits();
    }

    public double getAvgParticipant(){
        return participantRepository.getAvgParticipant();
    }

    public long getCountEventsInLastMonths() { return eventRepository.getCountEventsInLastMonths(); }

    public long getCountEventsInThisMonths() { return eventRepository.getCountEventsInThisMonths(); }

}
