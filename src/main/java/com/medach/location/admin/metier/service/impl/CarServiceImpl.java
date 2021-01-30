package com.medach.location.admin.metier.service.impl;


import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medach.location.admin.dao.entity.Car;
import com.medach.location.admin.dao.entity.Modele;
import com.medach.location.admin.dao.repository.CarRepository;
import com.medach.location.admin.dao.repository.ModeleRepository;
import com.medach.location.admin.metier.service.CarService;
import com.medach.location.admin.metier.service.FileStorageService;

/**
 * Service Implementation for managing {@link Car}.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;
    
    @Autowired
    private ModeleRepository modeleRepository;
    @Autowired 
    private FileStorageService storage;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Car save(Car car) {
       Optional<Modele> m = modeleRepository.findById(car.getModele().getId());
       car.setModele(m.get());
       log.debug(car.getId()+"");
       return carRepository.save(car);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Car> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carRepository.findAll(pageable);
    }


    /**
     * Get one car by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findById(id);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);
       String repertoire="C:\\Users\\biagr\\IdeaProjects\\ngx-admin\\src\\assets\\images\\"+id;
        File repository = new File(repertoire);
      storage.deleteAll(Paths.get(repertoire));
        carRepository.deleteById(id);
    }

	@Override
	public List<Car> getAll() {
		// TODO Auto-generated method stub
		return carRepository.findAll();
	}
}
