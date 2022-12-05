package mk.ukim.finki.labwp.service.Impl;


import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.labwp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.labwp.repository.jpa.UserJpaRepo;
import mk.ukim.finki.labwp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepo userJpaRepo;

    public UserServiceImpl(UserJpaRepo userJpaRepo) {
        this.userJpaRepo = userJpaRepo;
    }

    @Override
    public User register(String username, String password, String repeatPassword) {
       if (username==null || username.isEmpty() || password==null || password.isEmpty())
        throw new IllegalArgumentException();
       if (!password.equals(repeatPassword))
           throw new PasswordsDoNotMatchException();
       if (this.userJpaRepo.findByUsername(username).isPresent())
           throw new UsernameAlreadyExistsException(username);
       User user=new User(username,password);
       return userJpaRepo.save(user);
    }
}
