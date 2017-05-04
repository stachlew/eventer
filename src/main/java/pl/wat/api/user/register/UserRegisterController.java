package pl.wat.api.user.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wat.logic.event._model.EventCreateForm;
import pl.wat.logic.user._model.UserRegisterForm;
import pl.wat.logic.user.account.UserAccountService;
import pl.wat.logic.user.register.UserRegisterService;

@RestController
@RequestMapping("/api/user/register")
public class UserRegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @Autowired
    UserAccountService userAccountService;

    @PostMapping(value = "/postNewUser")
    public boolean createUser(@RequestBody UserRegisterForm rf) {
        if(rf!=null){
            return userRegisterService.createUser(rf);
        }
        return false;
    }

    @PostMapping(value = "/checkUsername")
    public boolean checkUsername(@RequestBody UserRegisterForm rf) {
        if(rf!=null){
            return userAccountService.isUsernameFree(rf.getUsername());
        }
        return false;
    }



}
