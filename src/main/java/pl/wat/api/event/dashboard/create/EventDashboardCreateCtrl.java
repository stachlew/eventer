package pl.wat.api.event.dashboard.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.event.Template;
import pl.wat.db.domain.event.location.Place;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.event.create.EventCreateService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/event/dashboard/create")
public class EventDashboardCreateCtrl {

    @Autowired
    private EventCreateService eventCreateService;

    @Autowired
    UserAccountService userAccountService;

    @PostMapping(value = "/postCreateEvent")
    @PreAuthorize("hasRole('USER')")
    public int CreateEvent(@RequestBody EventCreateForm ev,Authentication auth) {

        SecurityInfo si = new SecurityInfo(auth,userAccountService);

        if(si.isLogged()){
            User user = si.getDetails();
            return eventCreateService.processAndCreateEvent(ev,user);
        }
        else {
            return -1;
        }
    }
}
