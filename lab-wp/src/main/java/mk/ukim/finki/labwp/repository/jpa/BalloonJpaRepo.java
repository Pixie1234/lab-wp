package mk.ukim.finki.labwp.repository.jpa;

import mk.ukim.finki.labwp.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonJpaRepo extends JpaRepository<Balloon,Long> {

    List<Balloon> findAllByNameLike(String text);
    void deleteByName(String name);
}
