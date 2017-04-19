package pl.wat.logic.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.user.account.AuthorityService;
import pl.wat.logic.user.account.UserAccountService;

import java.util.Date;

@Service
public class UserRegisterService {

    @Autowired
    UserAccountService userAccountService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;

    public boolean createUser(User user){
        if(user!=null && userAccountService.isUsernameFree(user.getUsername())){
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
        if(user!=null && userAccountService.isUsernameFree(user.getUsername())){
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
}
