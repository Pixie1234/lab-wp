package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {


   List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> save(String name, String country, String address);
    void deleteById(Long id);
}
