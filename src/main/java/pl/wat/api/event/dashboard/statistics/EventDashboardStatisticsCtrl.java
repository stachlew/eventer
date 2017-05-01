package pl.wat.api.event.dashboard.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.event._model.dashboard.EventDashboardInfo;
import pl.wat.logic.event._model.dashboard.EventDashboardStatisticsInfo;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.event.dashboard.EventDashboardStatisticsService;
import pl.wat.logic.user._model.SecurityInfo;
import pl.wat.logic.user.account.UserAccountService;

@RestController
@RequestMapping("/api/event/dashboard/statistics")
public class EventDashboardStatisticsCtrl {

    @Autowired
    EventDashboardStatisticsService eventDashboardStatisticsService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    EventDashboardService eventDashboardService;


    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/getEventStats/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EventDashboardStatisticsInfo getEventStatisticsInfo(@PathVariable String id, Authentication auth){
        try {
            int intId = Integer.parseInt(id);
            SecurityInfo si = new SecurityInfo(auth,userAccountService);
            if(si.isEventOwner(intId,eventDashboardService)){
                return eventDashboardStatisticsService.getStatistics(intId);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
