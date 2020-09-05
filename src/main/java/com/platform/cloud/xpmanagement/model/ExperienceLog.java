package com.platform.cloud.xpmanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "experience_log")
public class ExperienceLog implements Serializable{
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "experience_log_id")
	@JsonIgnore
	private String experienceLogId;
	@Column(name = "amount", columnDefinition = "integer")
	@NotNull(message = "points is mandatory")
	private Integer points;
	@Enumerated(EnumType.STRING)
	private VarType varying;
	private String remarks;
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at_timestamp", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "experience_id", referencedColumnName="experience_id", nullable = false),
		@JoinColumn(name = "player_id", referencedColumnName="player_id", nullable = false)
	})
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Experience experience;
	public String getExperienceLogId() {
		return experienceLogId;
	}
	public void setExperienceLogId(String experienceLogId) {
		this.experienceLogId = experienceLogId;
	}

	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public VarType getVarying() {
		return varying;
	}
	public void setVarying(VarType varying) {
		this.varying = varying;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Experience getExperience() {
		return experience;
	}
	public void setExperience(Experience experience) {
		this.experience = experience;
	} 
}
