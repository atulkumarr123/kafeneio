package com.kafeneio.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="parent_child")
public class ParentChildModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long Id;
	@Column(name="parent_id")
	private Long parentId;
	@Column(name="node")
	private String position;
	 @Column(name="sponsorId")
	 private String sponsorId;
	 public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	@OneToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name = "registration_id" )
	// @Column(name="childId")
	private RegistrationModel registration;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public RegistrationModel getRegistration() {
		return registration;
	}
	public void setRegistration(RegistrationModel registration) {
		this.registration = registration;
	}
	
	

}
