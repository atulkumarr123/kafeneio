package com.kafeneio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafeneio.constants.ApplicationConstant;

@MappedSuperclass
public class BaseEntity implements Serializable{

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstant.DATE_FORMAT)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstant.DATE_FORMAT)
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="created_by")
 	private Users createdBy;

	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="last_updated_by")
	private Users lastUpdatedBy;



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}



	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}



	public Users getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}



	public Users getLastUpdatedBy() {
		return lastUpdatedBy;
	}



	public void setLastUpdatedBy(Users lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
