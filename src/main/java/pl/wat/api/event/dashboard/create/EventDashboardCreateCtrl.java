package pl.wat.api.event.dashboard.create;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.util.DictionaryService;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private DictionaryService dictionaryService;


}
