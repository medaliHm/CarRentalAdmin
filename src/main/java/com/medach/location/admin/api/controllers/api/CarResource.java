package com.medach.location.admin.api.controllers.api;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.medach.location.admin.api.controllers.account.util.PaginationUtil;
import com.medach.location.admin.api.controllers.account.util.ResponseUtil;
import com.medach.location.admin.api.controllers.errors.BadRequestAlertException;
import com.medach.location.admin.api.controllers.errors.HeaderUtil;
import com.medach.location.admin.api.dto.CarDto;
import com.medach.location.admin.api.dto.ResponseMessage;
import com.medach.location.admin.dao.entity.Car;
import com.medach.location.admin.metier.service.CarService;
import com.medach.location.admin.metier.service.FileStorageService;

/**
 * REST controller for managing {@link com.medach.location.domain.Car}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*",origins = "**")

public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "car";

    @Value("${medach.clientApp.name}")
    private String applicationName;

    private final CarService carService;


    @Autowired
    private FileStorageService storageService;
    public CarResource(CarService carService) {
        this.carService = carService;
    }

    /**
     * {@code POST  /cars} : Create a new car.
     *
     * @param car the car to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new car, or with status {@code 400 (Bad Request)} if the car has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody CarDto car) throws URISyntaxException {
        log.debug("REST request to save Car : {}", car);
       
        Car entity = car.dtoToCar();
        entity.setId(null);
        Car result = carService.save(entity);
        return ResponseEntity.created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cars} : Updates an existing car.
     *
     * @param car the car to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated car,
     * or with status {@code 400 (Bad Request)} if the car is not valid,
     * or with status {@code 500 (Internal Server Error)} if the car couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cars")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) throws URISyntaxException {
        log.debug("REST request to update Car : {}", car);
        if (car.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Car result = carService.save(car);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, car.getId().toString()))
            .body(result);
    }



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadImages(@RequestParam("file") MultipartFile file , @RequestParam("id") String id) throws URISyntaxException, IOException {
        String message = "";
        log.debug("Upload Files : {}", id);

        try {
            String repertoire="C:\\Users\\biagr\\IdeaProjects\\ngx-admin\\src\\assets\\images\\"+id;
            File repository = new File(repertoire);
            if(!repository.exists()){
                repository.mkdir();
            }

            storageService.save(file,repertoire);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

    
    @PostMapping("/upload/delete")
    public ResponseEntity<ResponseMessage> deleteImages(@RequestParam("file") String file , @RequestParam("id") String id) throws URISyntaxException, IOException {
        String message = "";
        log.debug("Upload Files : {}", id);

        try {
        	
           String repertoire="C:\\Users\\biagr\\IdeaProjects\\ngx-admin\\src\\assets\\images\\"+id+"\\"+file;
            File repository = new File(repertoire);
          

            storageService.delete(repository);
            message = "Deleted file successfully: " + file;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }


    /**
     * {@code GET  /cars} : get all the cars.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cars in body.
     */
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Cars");
        Page<Car> page = carService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
   
    }

    /**
     * {@code GET  /cars/:id} : get the "id" car.
     *
     * @param id the id of the car to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the car, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        log.debug("REST request to get Car : {}", id);
        Optional<Car> car = carService.findOne(id);
        return ResponseUtil.wrapOrNotFound(car);
    }

    /**
     * {@code DELETE  /cars/:id} : delete the "id" car.
     *
     * @param id the id of the car to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.debug("REST request to delete Car : {}", id);
        carService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
