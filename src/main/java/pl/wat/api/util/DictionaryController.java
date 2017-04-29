package pl.wat.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.EventType;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Region;
import pl.wat.logic.util.DictionaryService;

import java.util.List;

/**
 * Created by Przemohawryl on 25.04.2017.
 */

@RestController
@RequestMapping("/api/util/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @ResponseBody
    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return dictionaryService.getAllRegions();
    }

    @ResponseBody
    @GetMapping("/eventTypes")
    public List<EventType> getEventType() {
        return dictionaryService.getAllEventTypes();
    }

    @ResponseBody
    @GetMapping("/cities")
    public List<City> getCities(@RequestParam int idRegion) {
        return dictionaryService.getCitiesByIdRegion(idRegion);
    }
}
