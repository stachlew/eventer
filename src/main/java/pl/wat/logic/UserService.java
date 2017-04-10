package pl.wat.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;

import java.util.Date;

//TODO: Update usera, podejscie do usuwania usera
@Service
public class UserService {

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
        if(checkedUser!=null)
            return false;
        else
            return true;
    }

    public boolean createUser(User user){
        if(user!=null && isUsernameFree(user.getUsername())){
            String passwordHash = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordHash);
            user.setAuthorities(authorityService.getUserAuthority());
            user.setLastpassres(new Date());
            user.setEnabled(true); //DOMYSLNIE UZYTKOWNIK AKTYWOWANY
            if(userRepository.save(user)!=null)
                return true;
        }
        return false;
    }

    public boolean createAdmin(User user){
        if(user!=null && isUsernameFree(user.getUsername())){
            String passwordHash = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordHash);
            user.setAuthorities(authorityService.getAdminAuthority());
            user.setLastpassres(new Date());
            user.setEnabled(true); //DOMYSLNIE UZYTKOWNIK AKTYWOWANY
            user.setEnabled(true);
            if(userRepository.save(user)!=null)
                return true;
        }
        return false;
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

    public boolean disableUser(String username){
        User userToDisable = getUser(username);
        if(userToDisable!=null){
            userToDisable.setEnabled(false);
            userRepository.save(userToDisable);
            return true;
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
