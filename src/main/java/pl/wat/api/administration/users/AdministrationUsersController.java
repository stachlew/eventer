package pl.wat.api.administration.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.db.domain.user.User;
import pl.wat.logic.administration.users.AdministrationUsersService;
import pl.wat.logic.user._model.UserRegisterForm;
import pl.wat.logic.user.account.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/administration/users")
public class AdministrationUsersController {

  @Autowired
  private AdministrationUsersService administrationUsersService;

  @GetMapping("/getAllUsers")
  @ResponseBody
  public List<User> getAllUsers() {
    return administrationUsersService.getAllUsers();
  }

  @GetMapping("/getUserByEmail/{email}")
  @ResponseBody
  public User getUserByEmail(@PathVariable String email) {
    return administrationUsersService.getUserByEmail(email);
  }

  @GetMapping("/getUser/{id}")
  @ResponseBody
  public User getUser(@PathVariable int id) {
    return administrationUsersService.getUser(id);
  }

  /*@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/editUser/{id}")
  public void editUser(@RequestBody UserRegisterForm form) {
    if ( form != null) {
      userAccountService.updateUser(form);
    }
  }*/

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/disableUser/{id}")
  public void disableUser(@PathVariable int id) {
    administrationUsersService.disableUser(getUser(id).getUsername());
  }

}
