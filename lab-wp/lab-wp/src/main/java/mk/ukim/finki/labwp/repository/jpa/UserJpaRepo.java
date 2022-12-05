package mk.ukim.finki.labwp.repository.jpa;


import mk.ukim.finki.labwp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User,String> {
    Optional<User>findByUsernameAndPassword(String username,String password);
    Optional<User>findByUsername(String username);
}
