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
@Table(name="KAFENEIO_TYPE")
public class UserType extends BaseEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	
	@Column(name="type_code")
	private String TypeCode;
	

	@Column(name="type_desc")
	private String TypeDesc;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="master_type")
 	private MasterType masterType;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeCode() {
		return TypeCode;
	}
	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}
	public String getTypeDesc() {
		return TypeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		TypeDesc = typeDesc;
	}
	public MasterType getMasterType() {
		return masterType;
	}
	public void setMasterType(MasterType masterType) {
		this.masterType = masterType;
	}	
}
