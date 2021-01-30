package com.medach.location.admin.dao.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.medach.location.admin.dao.entity.Car;


/**
 * Spring Data  repository for the Car entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}