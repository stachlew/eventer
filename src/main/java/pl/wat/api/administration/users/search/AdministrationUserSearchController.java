package pl.wat.api.administration.users.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.user._model.UserAdministrationSearchForm;
import pl.wat.logic.user._model.UserAdministrationSearchResult;
import pl.wat.logic.user.search.UserSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/users/search")
public class AdministrationUserSearchController {

    @Autowired
    UserSearchService userSearchService;

    @RequestMapping(value = "/getSearchPage",method = RequestMethod.POST)
    @ResponseBody
    public List<UserAdministrationSearchResult> getEventsByAdminForm(@RequestBody UserAdministrationSearchForm form) {
        return userSearchService.findAdministrationUserPage(form);
    }

    @RequestMapping(value = "/getSearchFull",method = RequestMethod.POST)
    @ResponseBody
    public List<UserAdministrationSearchResult> getSearchFull(@RequestBody UserAdministrationSearchForm form){
        return userSearchService.findAdministrationUserFull(form);
    }


}
