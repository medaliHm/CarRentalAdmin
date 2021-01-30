package com.medach.location.admin.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medach.location.admin.dao.entity.Marque;

@SuppressWarnings("unused")
@Repository
public interface MarqueRepository  extends JpaRepository<Marque, Long> {

	
}
