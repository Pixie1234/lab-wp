package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.Role;
import mk.ukim.finki.labwp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, Role role);
}
