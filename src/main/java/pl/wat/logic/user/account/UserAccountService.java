package pl.wat.logic.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wat.api.event.dashboard.edit.EventDashboardEditCtrl;
import pl.wat.config.PasswordGenerator;
import pl.wat.db.domain.event.Event;
import pl.wat.db.domain.user.User;
import pl.wat.db.repository.event.EventRepository;
import pl.wat.db.repository.user.UserRepository;
import pl.wat.logic.event.dashboard.EventDashboardService;
import pl.wat.logic.user._model.UserChangeForm;
import pl.wat.logic.user.account.AuthorityService;
import pl.wat.logic.util.MailContainer;

import java.util.Date;

//TODO: Update usera, podejscie do usuwania usera
@Service
public class UserAccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    EventDashboardService eventDashboardService;

    @Autowired
    EventRepository eventRepository;

    public void sendMail(String mailTo, String subject, String message){
        MailContainer mailContainer = new MailContainer();
        mailContainer.sendMail("mail@eventer.pl",mailTo,subject,message);
    }

    public void sendMailAboutUserDelete(int idUser){
        User user = userRepository.getOne(idUser);
        if(user!=null){
            sendMail(user.getEmail(),"Usunięcie użytkownika : "+user.getUsername(),
                    "Użytkownik: "+user.getUsername()+" został usunięty z powodu naruszenia regulaminu."
                            + "\n \nPozdrawiamy, \n Zespół EVenter");
        }

    }

    public UserChangeForm getInfo(User user){
        UserChangeForm form;
        User dbUser;
        if(user!=null){
            dbUser = userRepository.getOne(user.getId());
            if(dbUser!=null){
                form = new UserChangeForm();
                form.setEmail(dbUser.getEmail());
                form.setUsername(dbUser.getUsername());
                form.setFirstname(dbUser.getFirstname());
                form.setLastname(dbUser.getLastname());
                form.setPhone(dbUser.getPhone());
                return form;
            }
        }
        return null;
    }

    public boolean changeUser(String username,UserChangeForm form){
        if(username!=null){

            User user = userRepository.findByUsername(username);
            if(user!=null){
                if(user.getUsername().equals(form.getUsername()) && PasswordGenerator.comparePassword(user.getPassword(),form.getOldPass())){

                    user.setFirstname(form.getFirstname());
                    user.setLastname(form.getLastname());
                    user.setEmail(form.getEmail());
                    user.setPhone(form.getPhone());
                    if(form.getNewPass()!=null && !form.getNewPass().equals("")) {

                        user.setPassword(PasswordGenerator.hashPassword(form.getNewPass()));
                         userRepository.save(user);
                         return true;
                    }

                    userRepository.save(user);
                    return true;
                }
            }

        }
        return false;
    }

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
            for (Event event : eventRepository.findAllByUserOrderByIdEvent(userToDelete)) {
                eventDashboardService.deleteEvent(event.getIdEvent());
            }
            userRepository.delete(userToDelete);
            return true;
            //CZY NIE WARTO WYCZYSCIC RESZTY ATRYBUTOW ZEBY NP ZWOLNIC USERNAME (kwestia NOT NULL)
        }
        return false;
    }

    public boolean deleteSelfUser(User user,String password){
        if(user!=null && password!=null && password.length()!=0 && PasswordGenerator.comparePassword(user.getPassword(),password)){
            for (Event event : eventRepository.findAllByUserOrderByIdEvent(user)) {
                eventDashboardService.deleteEvent(event.getIdEvent());
            }
            userRepository.delete(user);
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
