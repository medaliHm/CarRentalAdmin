package com.medach.location.admin.metier.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medach.location.admin.dao.entity.Marque;
import com.medach.location.admin.dao.repository.MarqueRepository;
import com.medach.location.admin.metier.service.MarqueService;

/**
 * Service Implementation for managing {@link Marque}.
 */
@Service
@Transactional
public class MarqueServiceImpl implements MarqueService {


    private final Logger log = LoggerFactory.getLogger(MarqueServiceImpl.class);

    private final MarqueRepository marqueRepository;

    public MarqueServiceImpl(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Marque save(Marque car) {
        log.debug("Request to save Marque : {}", car);
        return marqueRepository.save(car);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Marque> findAll(Pageable pageable) {
        log.debug("Request to get all Marques");
        return marqueRepository.findAll(pageable);
    }


    /**
     * Get one car by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Marque> findOne(Long id) {
        log.debug("Request to get Marque : {}", id);
        return marqueRepository.findById(id);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Marque : {}", id);
        marqueRepository.deleteById(id);
    }

	@Override
	public List<Marque> getAll() {
		// TODO Auto-generated method stub
		return marqueRepository.findAll();
	}

}
