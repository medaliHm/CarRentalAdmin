package com.medach.location.admin.api.controllers.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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

import com.medach.location.admin.api.controllers.account.util.PaginationUtil;
import com.medach.location.admin.api.controllers.account.util.ResponseUtil;
import com.medach.location.admin.api.controllers.errors.BadRequestAlertException;
import com.medach.location.admin.api.controllers.errors.HeaderUtil;
import com.medach.location.admin.dao.entity.Marque;
import com.medach.location.admin.metier.service.MarqueService;

@RestController
@RequestMapping("/api")
public class MarqueResource {

	
	   private final Logger log = LoggerFactory.getLogger(MarqueResource.class);

	    private static final String ENTITY_NAME = "marque";

	    @Value("${medach.clientApp.name}")
	    private String applicationName;

	    private final MarqueService marqueService;

	    public MarqueResource(MarqueService marqueService) {
	        this.marqueService = marqueService;
	    }

	    /**
	     * {@code POST  /marques} : Create a new marque.
	     *
	     * @param marque the marque to create.
	     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new marque, or with status {@code 400 (Bad Request)} if the marque has already an ID.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PostMapping("/marques")
	    public ResponseEntity<Marque> createMarque(@RequestBody Marque marque) throws URISyntaxException {
	        log.debug("REST request to save Marque : {}", marque);
	        if (marque.getId() != null) {
	            throw new BadRequestAlertException("A new marque cannot already have an ID", ENTITY_NAME, "idexists");
	        }
	        Marque result = marqueService.save(marque);
	        return ResponseEntity.created(new URI("/api/marques/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
	            .body(result);
	    }

	    /**
	     * {@code PUT  /marques} : Updates an existing marque.
	     *
	     * @param marque the marque to update.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated marque,
	     * or with status {@code 400 (Bad Request)} if the marque is not valid,
	     * or with status {@code 500 (Internal Server Error)} if the marque couldn't be updated.
	     * @throws URISyntaxException if the Location URI syntax is incorrect.
	     */
	    @PutMapping("/marques")
	    public ResponseEntity<Marque> updateMarque(@RequestBody Marque marque) throws URISyntaxException {
	        log.debug("REST request to update Marque : {}", marque);
	        if (marque.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	        }
	        Marque result = marqueService.save(marque);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, marque.getId().toString()))
	            .body(result);
	    }

	    /**
	     * {@code GET  /marques} : get all the marques.
	     *
	     * @param pageable the pagination information.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of marques in body.
	     */
	    @GetMapping("/marques")
	    public ResponseEntity<List<Marque>> getAllMarques(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
	        log.debug("REST request to get a page of Marques");
	        Page<Marque> page = marqueService.findAll(pageable);
	        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
	        return ResponseEntity.ok().headers(headers).body(page.getContent());
	    }

	    /**
	     * {@code GET  /marques/:id} : get the "id" marque.
	     *
	     * @param id the id of the marque to retrieve.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the marque, or with status {@code 404 (Not Found)}.
	     */
	    @GetMapping("/marques/{id}")
	    public ResponseEntity<Marque> getMarque(@PathVariable Long id) {
	        log.debug("REST request to get Marque : {}", id);
	        Optional<Marque> marque = marqueService.findOne(id);
	        return ResponseUtil.wrapOrNotFound(marque);
	    }

	    /**
	     * {@code DELETE  /marques/:id} : delete the "id" marque.
	     *
	     * @param id the id of the marque to delete.
	     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	     */
	    @DeleteMapping("/marques/{id}")
	    public ResponseEntity<Void> deleteMarque(@PathVariable Long id) {
	        log.debug("REST request to delete Marque : {}", id);
	        marqueService.delete(id);
	        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
	    }
}
