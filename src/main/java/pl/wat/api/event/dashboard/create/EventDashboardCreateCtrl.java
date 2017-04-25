package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.event.location.Region;
import pl.wat.db.repository.event.EventTypeRepository;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @PostMapping
    public int CreateEvent(@RequestBody EventCreateForm ev) {
        Event event = new Event.EventBuilder()
                .capacity(ev.getCapacity())
                .description(ev.getDescription())
                .startTime(ev.getStartTime())
                .endTime(ev.getEndTime())
                .freeEntrance(ev.isFreeEntrance())
                .eventType(eventTypeRepository.findByEventTypeName(ev.getEventTypeName()))
                .place(new Place.PlaceBuilder()
                        .streetName(ev.getStreetName())
                        .streetNo(ev.getStreetNo())
                        .geoLength(ev.getGeoLength())
                        .geoWidth(ev.getGeoWidth())
                        .city(new City.CityBuilder()
                                .cityName(ev.getCityName())
                                .region(new Region.RegionBuilder()
                                        .regionName(ev.getRegionName())
                                        .createRegion())
                                .createCity())
                        .createPlace())
                .createEvent();
        Event saved = eventCreateService.createEvent(event);
        return saved.getIdEvent();
    }
}
