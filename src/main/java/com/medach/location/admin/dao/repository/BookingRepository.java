package com.medach.location.admin.dao.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.medach.location.admin.dao.entity.Booking;


/**
 * Spring Data  repository for the Booking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
