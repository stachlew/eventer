package pl.wat.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import pl.wat.db.domain.user.Authority;
import pl.wat.db.domain.user.AuthorityName;
import pl.wat.db.domain.user.User;
import pl.wat.logic.UserService;

import java.util.List;


public class SecurityInfo {

    private boolean isLogged=false;
    private boolean isUser=false;
    private boolean isAdmin=false;
    private boolean haveUserAuth=false;
    private boolean haveAdminAuth=false;
    private String username;
    private User details;

    private UserService userService;
    private Authentication auth;

    public SecurityInfo(Authentication auth,UserService userService){
        if(auth!=null && userService!=null){
            this.auth=auth;
            this.isLogged=true;
            this.username=auth.getName();
            this.details=userService.getUser(this.username);
            List<Authority> authorities = this.details.getAuthorities();
            for (Authority a: authorities) {
                if(a.getName().equals(AuthorityName.ROLE_ADMIN))
                    this.haveAdminAuth=true;
                if(a.getName().equals(AuthorityName.ROLE_USER))
                    this.haveUserAuth=true;
            }
            if(this.haveUserAuth && this.haveUserAuth)
                this.isAdmin=true;
            if(this.haveUserAuth && !this.haveAdminAuth)
                this.isUser=true;
        }
    }


    public boolean isLogged() {
        if(auth!=null){
            return isLogged;
        }
        return false;
    }

    public boolean isUser() {
        if(auth!=null){
            return isUser;
        }
        return false;
    }

    public boolean isAdmin() {
        if(auth!=null){
            return isAdmin;
        }
        return false;
    }

    public boolean haveUserAuth() {
        if(auth!=null){
            return haveUserAuth;
        }
        return false;
    }

    public boolean haveAdminAuth() {
        if(auth!=null){
            return haveAdminAuth;
        }
        return false;
    }

    public String getUsername() {
        if(auth!=null){
            return username;
        }
        return null;
    }

    public User getDetails() {
        if(auth!=null){
            return details;
        }
        return null;
    }
}
