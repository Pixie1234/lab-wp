package mk.ukim.finki.labwp.service.Impl;

import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.labwp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.labwp.repository.jpa.UserJpaRepo;
import mk.ukim.finki.labwp.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepo userJpaRepo;

    public AuthServiceImpl(UserJpaRepo userJpaRepo) {

        this.userJpaRepo = userJpaRepo;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();

        }
        return userJpaRepo.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);

    }
}
