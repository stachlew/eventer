package pl.wat.api.event.dashboard.guests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.view.EventViewPartcipant;
import pl.wat.logic.event.dashboard.EventDashboardParticipantsService;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/event/dashboard/guests")
public class EventDashboardGuestsCtrl {


    @Autowired
    UserAccountService userAccountService;

    @Autowired
    EventDashboardService eventDashboardService;

    @Autowired
    EventDashboardParticipantsService eventDashboardParticipantsService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getList/{id}",method = RequestMethod.GET)
    public @ResponseBody
    List<EventViewPartcipant> getEventDashboardInfo(@PathVariable String id, Authentication auth){
        try {
            int intId = Integer.parseInt(id);
            SecurityInfo si = new SecurityInfo(auth,userAccountService);
            if(si.isEventOwner(intId,eventDashboardService)){
                return eventDashboardParticipantsService.getParticipants(intId);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/setPresence",method = RequestMethod.POST)
    boolean setParticipantPresence (@RequestBody EventViewPartcipant eventViewPartcipant){
        if (eventViewPartcipant!=null){
            return eventDashboardParticipantsService.editParticipantPresence(eventViewPartcipant);
        }
        return false;

    }

}
