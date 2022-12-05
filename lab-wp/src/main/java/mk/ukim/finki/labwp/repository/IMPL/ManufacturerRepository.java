package mk.ukim.finki.labwp.repository.IMPL;

import mk.ukim.finki.labwp.bootstrap.DataHolder;
import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    public List<Manufacturer>findAll(){

        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {

        return DataHolder.manufacturers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Manufacturer> save(String name, String country,String address) {
        Manufacturer manufacturer = new Manufacturer(name, country,address);
        DataHolder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }

    public boolean deleteById(Long id) {
        return DataHolder.manufacturers.removeIf(i -> i.getId().equals(id));
    }


}
