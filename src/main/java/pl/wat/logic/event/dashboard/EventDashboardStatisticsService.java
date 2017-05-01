package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.OpinionRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardStatisticsInfo;

@Service
public class EventDashboardStatisticsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    OpinionRepository opinionRepository;

    public EventDashboardStatisticsInfo getStatistics(int id) {
        if(eventRepository.exists(id)) {
            Event event = eventRepository.getOne(id);
            int visits = event.getVisits();
            int participants = participantRepository.countByEvent(event);
            int presence = participantRepository.countByEventAndPresenceIsTrue(event);
            EventDashboardStatisticsInfo stats = new EventDashboardStatisticsInfo(visits, participants, presence);
            //pobieranie gwiazdek
            stats.setStars1(opinionRepository.countByEventIdEventAndRate(id,1));
            stats.setStars2(opinionRepository.countByEventIdEventAndRate(id,2));
            stats.setStars3(opinionRepository.countByEventIdEventAndRate(id,3));
            stats.setStars4(opinionRepository.countByEventIdEventAndRate(id,4));
            stats.setStars5(opinionRepository.countByEventIdEventAndRate(id,5));


            return stats;
        }
        return null;
    }
}
