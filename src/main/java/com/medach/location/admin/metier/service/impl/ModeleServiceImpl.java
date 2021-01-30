package com.medach.location.admin.metier.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medach.location.admin.dao.entity.Marque;
import com.medach.location.admin.dao.entity.Modele;
import com.medach.location.admin.dao.repository.MarqueRepository;
import com.medach.location.admin.dao.repository.ModeleRepository;
import com.medach.location.admin.metier.service.ModeleService;

/**
 * Service Implementation for managing {@link Modele}.
 */
@Service
@Transactional
public class ModeleServiceImpl implements ModeleService {


    private final Logger log = LoggerFactory.getLogger(ModeleServiceImpl.class);
    
    @Autowired
    private MarqueRepository marqueRepository;
    private final ModeleRepository modeleRepository;

    public ModeleServiceImpl(ModeleRepository modeleRepository) {
        this.modeleRepository = modeleRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Modele save(Modele car) {
    	Optional<Marque> m = marqueRepository.findById(car.getMarque().getId());
    	car.setMarque(m.get());
        log.debug("Request to save Modele : {}", car);
        return modeleRepository.save(car);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Modele> findAll(Pageable pageable) {
        log.debug("Request to get all Modeles");
        return modeleRepository.findAll(pageable);
    }
    


    /**
     * Get one car by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Modele> findOne(Long id) {
        log.debug("Request to get Modele : {}", id);
        return modeleRepository.findById(id);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modele : {}", id);
        modeleRepository.deleteById(id);
    }

	@Override
	public List<Modele> getAll() {
		// TODO Auto-generated method stub
		return modeleRepository.findAll();
	}

	@Override
	public List<Modele> modelesByMarque(Long idMarque) {
		// TODO Auto-generated method stub
		return modeleRepository.findModeleByMarque(idMarque);
	}

}
