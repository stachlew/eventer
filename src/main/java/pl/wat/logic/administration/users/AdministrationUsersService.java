package pl.wat.logic.administration.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.user.account.UserAccountService;

import java.util.List;

@Service
public class AdministrationUsersService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserAccountService userAccountService;

  public List<User> getAllUsers() { return userRepository.findAll(); }

  public User getUserByEmail(String email) { return userRepository.findByEmail(email); }

  public User getUser(int id) { return userRepository.findOne(id); }

  public boolean disableUser(String username){
    User userToDisable = userAccountService.getUser(username);
    if(userToDisable!=null){
      userToDisable.setEnabled(false);
      userRepository.save(userToDisable);
      return true;
    }
    return false;
  }
}
