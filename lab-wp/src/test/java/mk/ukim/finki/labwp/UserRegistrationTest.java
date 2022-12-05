package mk.ukim.finki.labwp;


import mk.ukim.finki.labwp.model.Role;
import mk.ukim.finki.labwp.model.User;
import mk.ukim.finki.labwp.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.labwp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.labwp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.labwp.repository.jpa.UserJpaRepo;
import mk.ukim.finki.labwp.service.Impl.UserServiceImpl;
import mk.ukim.finki.labwp.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
//JUNIt testing
@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserJpaRepo userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password",  Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() {
        User user = this.service.register("username", "password", "password",  Role.ROLE_USER);

        Mockito.verify(this.service).register("username", "password", "password",  Role.ROLE_USER);


        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());

        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());
    }


    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null, "password", "password",  Role.ROLE_USER));
        Mockito.verify(this.service).register(null, "password", "password", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "password", "password",  Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password",  Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password",  Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password",  Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, password, "password",  Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, "password",  Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username, password, confirmPassword,  Role.ROLE_USER));
        Mockito.verify(this.service).register(username, password, confirmPassword,  Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password", "password",  Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password",  Role.ROLE_USER);
    }


}
