package pl.wat.logic.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.user._model.UserRegisterForm;
import pl.wat.logic.user.account.AuthorityService;

import java.util.Date;
import java.util.List;

//TODO: Update usera, podejscie do usuwania usera
@Service
public class UserAccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public boolean isUsernameFree(String username){
        User checkedUser = getUser(username);
        if(checkedUser!=null || username == null || username.equals(""))
            return false;
        else
            return true;
    }

    public boolean deleteUser(String username){
        User userToDelete = getUser(username);
        if(userToDelete!=null){
            userToDelete.setEnabled(false);
            userRepository.save(userToDelete);
            return true;
            //CZY NIE WARTO WYCZYSCIC RESZTY ATRYBUTOW ZEBY NP ZWOLNIC USERNAME (kwestia NOT NULL)
        }
        return false;
    }

    public boolean enableUser(String username){
        User userToEnable = getUser(username);
        if(userToEnable!=null){
            userToEnable.setEnabled(true);
            userRepository.save(userToEnable);
            return true;
        }
        return false;
    }
}
