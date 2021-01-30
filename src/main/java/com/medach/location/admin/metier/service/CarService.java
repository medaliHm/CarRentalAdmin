package com.medach.location.admin.metier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.medach.location.admin.dao.entity.Car;

/**
 * Service Interface for managing {@link Car}.
 */
public interface CarService {

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    Car save(Car car);

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Car> findAll(Pageable pageable);
    List<Car> getAll();


    /**
     * Get the "id" car.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Car> findOne(Long id);

    /**
     * Delete the "id" car.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
