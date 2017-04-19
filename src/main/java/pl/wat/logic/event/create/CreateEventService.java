package pl.wat.logic.event.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;

@Service
public class CreateEventService {

    @Autowired
    EventRepository eventRepository;

    //stworzenie wydarzenia
    //TODO: Dopracowac metode dodawania eventow do bazy. Aktualnie dosc trywialna
    public boolean createEvent(Event event){
        if(event!=null && eventRepository.exists(event.getIdEvent())){
            Event saved = eventRepository.save(event);
            if(saved!=null)
                return true;
        }
        return false;
    }
}
