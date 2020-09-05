package com.platform.cloud.xpmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cloud.xpmanagement.model.Experience;
import com.platform.cloud.xpmanagement.model.ExperienceLog;
import com.platform.cloud.xpmanagement.model.VarType;
import com.platform.cloud.xpmanagement.repository.ExperienceLogRepository;
import com.platform.cloud.xpmanagement.repository.ExperienceRepository;



@RestController
public class ExperienceController {
	private static final Logger logger = LogManager.getLogger(ExperienceController.class);
	
	@Autowired
	private ExperienceLogRepository experienceLogRepository;
	
	@Autowired
	private ExperienceRepository experienceRepository;
	
	@PostMapping("/experience/{player_id}")
    public ExperienceLog addExperience(@PathVariable Long player_id,@Valid @RequestBody ExperienceLog experienceLog) {
		if(experienceLog.getPoints() < 0) {
			experienceLog.setVarying(VarType.PENALTY);
		}else {
			experienceLog.setVarying(VarType.EARN);
		}
		if(experienceRepository.findbyPlayerId(player_id) != null) {
			Experience experience = experienceRepository.findbyPlayerId(player_id);
			experience.setBalance(experienceLogRepository.findbyPlayerId(player_id).stream().collect(Collectors.summingInt(ExperienceLog::getPoints)) + experienceLog.getPoints());
			experienceLog.setExperience(experience);;
			experienceLogRepository.save(experienceLog);			
		}else {
			Experience experience = new Experience();
			experience.setBalance(experienceLog.getPoints());
			experience.setPlayerId(player_id);
			experienceLog.setExperience(experience);
			experienceLogRepository.save(experienceLog);
		}
		return experienceLog;
    }
	
	@GetMapping("/experience/{player_id}")
	public Experience getExperience(@PathVariable Long player_id) {
		if(experienceRepository.findbyPlayerId(player_id) != null){
			return experienceRepository.findbyPlayerId(player_id);
		}else {
			Experience experience = new Experience();
			experience.setPlayerId(player_id);
			experience.setBalance(0);
			return experienceRepository.save(experience);
		}
		
	}
}
