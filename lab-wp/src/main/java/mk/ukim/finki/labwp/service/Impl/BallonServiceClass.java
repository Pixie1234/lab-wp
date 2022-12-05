package mk.ukim.finki.labwp.service.Impl;


import mk.ukim.finki.labwp.model.Balloon;
import mk.ukim.finki.labwp.model.Manufacturer;
import mk.ukim.finki.labwp.model.exceptions.BalloonNotFoundException;
import mk.ukim.finki.labwp.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.labwp.repository.IMPL.BalloonRepository;
import mk.ukim.finki.labwp.repository.IMPL.ManufacturerRepository;
import mk.ukim.finki.labwp.repository.jpa.BalloonJpaRepo;
import mk.ukim.finki.labwp.repository.jpa.ManufacturerJpaRepo;
import mk.ukim.finki.labwp.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BallonServiceClass implements BalloonService {
    public BallonServiceClass( BalloonJpaRepo balloonJpaRepo, ManufacturerJpaRepo manufacturerRepository) {
        this.balloonJpaRepo = balloonJpaRepo;

        this.manufacturerRepository = manufacturerRepository;
    }

    private final BalloonJpaRepo balloonJpaRepo;
    private final ManufacturerJpaRepo manufacturerRepository;

    @Override
    public List<Balloon> listAll() {

        return balloonJpaRepo.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonJpaRepo.findAllByNameLike(text);
    }



    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        this.balloonJpaRepo.deleteByName(name);
        return Optional.of(this.balloonJpaRepo.save(new Balloon(name,description,manufacturer)));
               //this.balloonJpaRepo.save(name,description,manufacturer);

    }

    @Override
    public void deleteById(Long id) {
        this.balloonJpaRepo.deleteById(id);

    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonJpaRepo.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId) {
        Balloon balloon = this.balloonJpaRepo.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));

        balloon.setName(name);
        balloon.setDescription(description);
        //balloon.setManufacturer(manufacturer);



        Manufacturer manufacturer = this.manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        balloon.setManufacturer(manufacturer);

        return Optional.of(this.balloonJpaRepo.save(balloon));

    }
}
