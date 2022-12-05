package mk.ukim.finki.labwp.service.Impl;


import mk.ukim.finki.labwp.model.Role;
import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.labwp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.labwp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.labwp.repository.jpa.UserJpaRepo;
import mk.ukim.finki.labwp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepo userJpaRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserJpaRepo userJpaRepo, PasswordEncoder passwordEncoder) {
        this.userJpaRepo = userJpaRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, Role userRole) {
       if (username==null || username.isEmpty() || password==null || password.isEmpty())
        throw new InvalidUsernameOrPasswordException();
       if (!password.equals(repeatPassword))
           throw new PasswordsDoNotMatchException();
       if (this.userJpaRepo.findByUsername(username).isPresent())
           throw new UsernameAlreadyExistsException(username);
       User user=new User(username, passwordEncoder.encode(password),userRole);
       return userJpaRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
