package pl.wat.logic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.repository.event.EventTypeRepository;
import pl.wat.db.repository.event.location.RegionRepository;

import java.util.List;

/**
 * Created by Przemohawryl on 25.04.2017.
 */
@Service
public class DictionaryService {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    RegionRepository regionRepository;

    public List<Region> getAllRegions(){
        return regionRepository.findAll();
    }

    public List<EventType> getAllEventTypes(){
        return eventTypeRepository.findAll();
    }
}
