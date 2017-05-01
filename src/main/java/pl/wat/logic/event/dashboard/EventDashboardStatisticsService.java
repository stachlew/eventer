package pl.wat.logic.event.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.ParticipantRepository;
import pl.wat.logic.event._model.dashboard.EventDashboardStatisticsInfo;

@Service
public class EventDashboardStatisticsService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    public EventDashboardStatisticsInfo getStatistics(int id) {
        if(eventRepository.exists(id)) {
            Event event = eventRepository.getOne(id);
            int visits = event.getVisits();
            int participants = participantRepository.countByEvent(event);
            int presence = participantRepository.countByEventAndPresenceIsTrue(event);
            EventDashboardStatisticsInfo stats = new EventDashboardStatisticsInfo(visits, participants, presence);
            //pobieranie gwiazdek
//            stats.setStars1(participantRepository.);
//            stats.setStars2(participantRepository.);
//            stats.setStars3(participantRepository.);
//            stats.setStars4(participantRepository.);
//            stats.setStars5(participantRepository.);


            return stats;
        }
        return null;
    }
}
