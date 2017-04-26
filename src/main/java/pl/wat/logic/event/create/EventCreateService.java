package pl.wat.logic.event.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.Event;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.event.location.PlaceRepository;

@Service
public class EventCreateService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlaceRepository placeRepository;

    //stworzenie wydarzenia
    //TODO: Dopracowac metode dodawania eventow do bazy. Aktualnie dosc trywialna
    public Event save(Event event){
        if(event!=null){
            placeRepository.save(event.getPlace());
            Event saved = eventRepository.save(event);
            if(saved!=null) return saved;
        }
        return null;
    }
}
