package pl.wat.api.event.dashboard.edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.dashboard.EventDashboardInfo;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

@RestController
@RequestMapping("/api/event/dashboard/edit")
public class EventDashboardEditCtrl {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    EventDashboardService eventDashboardService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getEventInfo/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EventDashboardInfo getEventDashboardInfo(@PathVariable String id, Authentication auth){
        try {
            int intId = Integer.parseInt(id);
            SecurityInfo si = new SecurityInfo(auth,userAccountService);
            if(si.isEventOwner(intId,eventDashboardService)){
                return eventDashboardService.getEventDashboardInfo(intId);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/updateEvent",method = RequestMethod.POST)
    public @ResponseBody
    Boolean getEventDashboardInfo(@RequestBody EventDashboardInfo form, Authentication auth){
        try {
            SecurityInfo si = new SecurityInfo(auth,userAccountService);
            if(form!=null && si.isEventOwner(form.getIdEvent(),eventDashboardService)){
                System.out.println(form.getPlace().getStreetName());
                System.out.println(form.getPlace().getCity().getCityName());
                System.out.println(form.getPlace().getCity().getRegion().getRegionName());
                System.out.println(form.getStartTime());
                System.out.println(form.getEndTime());
                return eventDashboardService.editEvent(form);
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }





}
