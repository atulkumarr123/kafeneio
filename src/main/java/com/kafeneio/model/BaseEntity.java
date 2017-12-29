package com.kafeneio.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kafeneio.constants.ApplicationConstant;

public class BaseEntity {

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

}
