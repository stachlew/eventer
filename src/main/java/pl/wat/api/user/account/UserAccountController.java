package pl.wat.api.user.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.wat.logic.user._model.UserChangeForm;
import pl.wat.logic.user.account.UserAccountService;

@RestController
@RequestMapping("/api/user/account")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;
///api/user/account/getInfo
    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public @ResponseBody
    UserChangeForm getInfo(Authentication auth){
        if(auth!=null){
            return userAccountService.getInfo(userAccountService.getUser(auth.getName()));
        }
        return null;
    }

    @RequestMapping(value = "/postUser",method = RequestMethod.POST)
    public @ResponseBody
    boolean postChanges(Authentication auth, @RequestBody UserChangeForm form){
        if(auth!=null){
            return userAccountService.changeUser(auth.getName(),form);
        }
        return false;
    }
}
