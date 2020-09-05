package com.platform.cloud.xpmanagement.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.cloud.xpmanagement.model.ExperienceLog;


@Repository
public interface ExperienceLogRepository extends JpaRepository<ExperienceLog, Serializable>{
	@Query(value = "SELECT * FROM experience_log t WHERE t.player_id = ?1", nativeQuery = true)
	List<ExperienceLog> findbyPlayerId(Long playerId);
}
