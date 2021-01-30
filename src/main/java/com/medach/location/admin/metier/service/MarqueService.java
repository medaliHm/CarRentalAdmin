package com.medach.location.admin.metier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.medach.location.admin.dao.entity.Marque;

public interface MarqueService {

	
	   /**
     * Save a Marque.
     *
     * @param Marque the entity to save.
     * @return the persisted entity.
     */
    Marque save(Marque Marque);

    /**
     * Get all the Marques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Marque> findAll(Pageable pageable);
    List<Marque> getAll();


    /**
     * Get the "id" Marque.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Marque> findOne(Long id);

    /**
     * Delete the "id" Marque.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
