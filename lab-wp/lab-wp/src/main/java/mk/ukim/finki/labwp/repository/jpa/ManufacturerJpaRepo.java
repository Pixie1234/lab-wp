package mk.ukim.finki.labwp.repository.jpa;


import mk.ukim.finki.labwp.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerJpaRepo  extends JpaRepository<Manufacturer,Long> {
}
