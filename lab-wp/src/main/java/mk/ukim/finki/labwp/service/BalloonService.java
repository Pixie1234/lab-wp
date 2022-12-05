package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Optional<Balloon> save(String name, String description,Long manufacturer);
    void deleteById(Long id);
    Optional<Balloon> findById(Long id);
    Optional<Balloon>edit(Long id,String name, String description, Long manufacturer);
}
