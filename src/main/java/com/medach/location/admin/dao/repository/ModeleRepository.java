package com.medach.location.admin.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medach.location.admin.dao.entity.Modele;
@SuppressWarnings("unused")
@Repository
public interface ModeleRepository extends JpaRepository<Modele, Long> {

	
	@Query("Select m from Modele m where m.marque.id=:marque_id")
	public List<Modele> findModeleByMarque(@Param("marque_id") Long marque_id);

}
