package com.medach.location.admin.metier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.medach.location.admin.dao.entity.Modele;

public interface ModeleService {

	
	   /**
     * Save a Modele.
     *
     * @param Modele the entity to save.
     * @return the persisted entity.
     */
    Modele save(Modele Modele);

    /**
     * Get all the Modeles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Modele> findAll(Pageable pageable);
    List<Modele> getAll();
    List<Modele> modelesByMarque(Long idMarque);
    


    /**
     * Get the "id" Modele.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Modele> findOne(Long id);

    /**
     * Delete the "id" Modele.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
