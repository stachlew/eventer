package pl.wat.logic;

import org.springframework.stereotype.Service;
import pl.wat.db.domain.user.Authority;
import pl.wat.db.domain.user.AuthorityName;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuthorityService {

    public List<Authority> getUserAuthority(){
        List<Authority> authorities = new LinkedList<>();
        Authority authorityUser = new Authority();
        authorityUser.setId(1);
        authorityUser.setName(AuthorityName.ROLE_USER);
        authorities.add(authorityUser);
        return authorities;
    }

    public List<Authority> getAdminAuthority(){
        List<Authority> authorities = getUserAuthority();
        Authority authorityAdmin = new Authority();
        authorityAdmin.setId(2);
        authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
        authorities.add(authorityAdmin);
        return authorities;
    }

}
