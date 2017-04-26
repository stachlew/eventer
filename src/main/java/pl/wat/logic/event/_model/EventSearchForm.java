package pl.wat.logic.event._model;


import pl.wat.db.domain.event.location.City;
import pl.wat.db.domain.event.location.Region;

import java.sql.Timestamp;

public class EventSearchForm {
    private String title;
    private String description;
    private Region region;
    private City city;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private boolean freeEntrance;
    private boolean registerEnabled;


}
