package com.kafeneio.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class RawMaterialDto {

	private Long id;
	private String rawMaterialCode;
	private String rawMaterialDesc;
	private Date creationDate;
	private Date lastUpdatedDate;
	private String quantity;
	private String lowerLimit;
 	private String unitDesc;
 	private Long unitValue;
 	private String remarks;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRawMaterialCode() {
		return rawMaterialCode;
	}
	public void setRawMaterialCode(String rawMaterialCode) {
		this.rawMaterialCode = rawMaterialCode;
	}
	public String getRawMaterialDesc() {
		return rawMaterialDesc;
	}
	public void setRawMaterialDesc(String rawMaterialDesc) {
		this.rawMaterialDesc = rawMaterialDesc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public Long getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(Long unitValue) {
		this.unitValue = unitValue;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	
 	
}
