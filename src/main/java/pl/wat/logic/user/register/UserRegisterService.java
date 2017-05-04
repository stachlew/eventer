package pl.wat.logic.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.user._model.UserRegisterForm;
import pl.wat.logic.user.account.AuthorityService;
import pl.wat.logic.user.account.UserAccountService;
import pl.wat.logic.util.MailContainer;

import java.util.Date;
import java.util.UUID;

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

    public void sendUserRegisterMail(String mailTo, String subject, String message){
        MailContainer mailContainer = new MailContainer();
        mailContainer.sendMail("mail@eventer.pl",mailTo,subject,message);
    }

    public boolean createUser(UserRegisterForm userForm){
        if(userForm!=null && userAccountService.isUsernameFree(userForm.getUsername())){
            String password = UUID.randomUUID().toString().substring(0,8);
            String passwordHash = passwordEncoder.encode(password);
            User user=new User(userForm.getUsername(),passwordHash,userForm.getFirstname(),userForm.getLastname(),userForm.getEmail(),userForm.getPhone());
            user.setAuthorities(authorityService.getUserAuthority());
            user.setLastpassres(new Date());
            user.setEnabled(true); //DOMYSLNIE UZYTKOWNIK AKTYWOWANY
            if(userRepository.save(user)!=null){
                System.out.println(password);
                sendUserRegisterMail(userForm.getEmail(),"Rejestracja użytkownika na portal eventer.pl",
                        "Dokonano rejestracji użytkownika "
                                + "\n \nHasło: "
                                + password
                                + "\n \n Po zalogowaniu do portalu, natychmiast zmień hasło."
                                + "\n Jeśli nie rejestrowałeś się na portal www.eventer.pl, zignoruj tą wiadomość."
                                + "\n \nPozdrawiamy, \n Zespół EVenter");
                return true;
            }

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
