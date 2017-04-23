package pl.wat.logic.event.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Opinion;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.OpinionRepository;
import pl.wat.logic.event._model.EventViewOpinion;
import pl.wat.logic.event._model.EventViewOpinionForm;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventOpinionService {

    @Autowired
    OpinionRepository opinionRepository;

    @Autowired
    EventRepository eventRepository;

    public List<EventViewOpinion> getOpinionsByIdEvent(int idEvent){
        Event event = eventRepository.getOne(idEvent);
        if(event!=null) {
            List<Opinion> opinions =  opinionRepository.getAllByEventOrderByCreateDate(event);
            List<EventViewOpinion> opinionsView = new LinkedList<>();
            if(opinions!=null){
                for (Opinion opinion:opinions) {
                    opinionsView.add(new EventViewOpinion(opinion));
                }
                return opinionsView;
            }
        }
        return null;
    }

    public boolean createOpinion(EventViewOpinionForm form){
        if(form!=null){
            Opinion opinion = new Opinion(form);
            Event event = eventRepository.getOne(form.getIdEvent());
            if(event!=null) {
                opinion.setEvent(event);
                try {
                    opinionRepository.save(opinion);
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
