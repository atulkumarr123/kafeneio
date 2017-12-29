package com.kafeneio.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafeneio.constants.ApplicationConstant;


@Entity
@Table(name="KAFENEIO_MASTER_TYPE")
public class MasterType extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	
	@Column(name="master_type_code")
	private String masterTypeCode;
	

	@Column(name="master_type_desc")
	private String masterTypeDesc;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMasterTypeCode() {
		return masterTypeCode;
	}
	public void setMasterTypeCode(String masterTypeCode) {
		this.masterTypeCode = masterTypeCode;
	}
	public String getMasterTypeDesc() {
		return masterTypeDesc;
	}
	public void setMasterTypeDesc(String masterTypeDesc) {
		this.masterTypeDesc = masterTypeDesc;
	}	
}
