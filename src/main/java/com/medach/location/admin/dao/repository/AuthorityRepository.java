package com.medach.location.admin.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medach.location.admin.dao.entity.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
