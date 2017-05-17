package pl.wat.api.administration.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.db.domain.user.User;
import pl.wat.logic.administration.users.AdministrationUsersService;
import pl.wat.logic.user._model.UserAdministrationSearchForm;
import pl.wat.logic.user._model.UserAdministrationSearchResult;
import pl.wat.logic.user.account.UserAccountService;
import pl.wat.logic.user.search.UserSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/users")
public class AdministrationUsersController {

  @Autowired
  private AdministrationUsersService administrationUsersService;
  @Autowired
  private UserSearchService userSearchService;
  @Autowired
  private UserAccountService userAccountService;

  @GetMapping("/getUser/{id}")
  @ResponseBody
  public User getUser(@PathVariable int id) {
    return administrationUsersService.getUser(id);
  }

  @PostMapping("/deleteUser/{id}")
  public List<UserAdministrationSearchResult> deleteUser(@PathVariable int id) {
    userAccountService.deleteUser(getUser(id).getUsername());
    return userSearchService.findAdministrationUserFull(new UserAdministrationSearchForm("", 0));
  }

}
