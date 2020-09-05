package com.platform.cloud.xpmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.cloud.xpmanagement.model.Experience;



@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
	@Query("SELECT t FROM Experience t WHERE t.playerId = ?1")
	Experience findbyPlayerId(Long playerId);
}
