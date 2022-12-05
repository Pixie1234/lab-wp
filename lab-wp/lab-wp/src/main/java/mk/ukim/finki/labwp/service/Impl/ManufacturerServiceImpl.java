package mk.ukim.finki.labwp.service.Impl;

import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.repository.IMPL.ManufacturerRepository;
import mk.ukim.finki.labwp.repository.jpa.ManufacturerJpaRepo;
import mk.ukim.finki.labwp.service.ManufacturerService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerJpaRepo manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerJpaRepo manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {

        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String country, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name,country,address)));
                //this.manufacturerRepository.save(name,country, address);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);

    }
}
