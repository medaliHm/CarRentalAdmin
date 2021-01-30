package com.medach.location.admin.api.controllers.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.medach.location.admin.api.controllers.account.util.ResponseUtil;
import com.medach.location.admin.api.controllers.errors.BadRequestAlertException;
import com.medach.location.admin.api.controllers.errors.HeaderUtil;
import com.medach.location.admin.api.dto.ModeleDto;
import com.medach.location.admin.dao.entity.Modele;
import com.medach.location.admin.metier.service.ModeleService;

/**
 * REST controller for managing {@link com.medach.location.domain.Modele}.
 */
@RestController
@RequestMapping("/api")
public class ModeleResource {
	   private final Logger log = LoggerFactory.getLogger(ModeleResource.class);

	    private static final String ENTITY_NAME = "modele";

	    @Value("${medach.clientApp.name}")
	    private String applicationName;

	    private final ModeleService modeleService;

	    public ModeleResource(ModeleService modeleService) {
	        this.modeleService = modeleService;
	    }

	    /**
	     * {@code POST  /modeles} : Create a new modele.
	     *
	     * @param modele the modele to create.
	     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modele, or with status {@code 400 (Bad Request)} if the modele has already an ID.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PostMapping("/modeles")
	    public ResponseEntity<Modele> createModele(@RequestBody ModeleDto modele) throws URISyntaxException {
	        log.debug("REST request to save Modele : {}", modele);
	      Modele entity = modele.dtoToModele();
	        Modele result = modeleService.save(entity);
	        return ResponseEntity.created(new URI("/api/modeles/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
	            .body(result);
	    }

	    /**
	     * {@code PUT  /modeles} : Updates an existing modele.
	     *
	     * @param modele the modele to update.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modele,
	     * or with status {@code 400 (Bad Request)} if the modele is not valid,
	     * or with status {@code 500 (Internal Server Error)} if the modele couldn't be updated.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PutMapping("/modeles")
	    public ResponseEntity<Modele> updateModele(@RequestBody ModeleDto modele) throws URISyntaxException {
	        log.debug("REST request to update Modele : {}", modele);
	        if (modele.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	        }
	        Modele entity = modele.dtoToModeleUpdate();
	        Modele result = modeleService.save(entity);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modele.getId().toString()))
	            .body(result);
	    }

	    /**
	     * {@code GET  /modeles} : get all the modeles.
	     *
	     * @param pageable the pagination information.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modeles in body.
	     */
	    @GetMapping("/modeles")
	    public ResponseEntity<List<Modele>> getAllModeles(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
	     return ResponseEntity.ok().body(modeleService.getAll());
	    }
	    
	    @GetMapping("/modeles/marque/{id_marque}")
	    public ResponseEntity<List<Modele>> getAllModelesByMarque(@PathVariable("id_marque") Long id_marque) {
	        log.debug("REST request to get a page of Modeles");
	        List<Modele> page = modeleService.modelesByMarque(id_marque);
	        return ResponseEntity.ok().body(page);
	    }

	    /**
	     * {@code GET  /modeles/:id} : get the "id" modele.
	     *
	     * @param id the id of the modele to retrieve.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modele, or with status {@code 404 (Not Found)}.
	     */
	    @GetMapping("/modeles/{id}")
	    public ResponseEntity<Modele> getModele(@PathVariable Long id) {
	        log.debug("REST request to get Modele : {}", id);
	        Optional<Modele> modele = modeleService.findOne(id);
	        return ResponseUtil.wrapOrNotFound(modele);
	    }

	    /**
	     * {@code DELETE  /modeles/:id} : delete the "id" modele.
	     *
	     * @param id the id of the modele to delete.
	     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	     */
	    @DeleteMapping("/modeles/{id}")
	    public ResponseEntity<Void> deleteModele(@PathVariable Long id) {
	        log.debug("REST request to delete Modele : {}", id);
	        modeleService.delete(id);
	        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
	    }
}
