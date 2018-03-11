package com.kafeneio.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryDto {

	private Long id;
	private Long foodItemsId;
	private String foodItemsDesc;
	private Long rawMaterialId;
	private String rawMaterialDesc;
	/*private Long unitsId;
	private String unitsDesc;
	*/private BigDecimal quantity;
	private String remarks;
	private Date creationDate;
	private Date lastUpdatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFoodItemsId() {
		return foodItemsId;
	}
	public void setFoodItemsId(Long foodItemsId) {
		this.foodItemsId = foodItemsId;
	}
	public String getFoodItemsDesc() {
		return foodItemsDesc;
	}
	public void setFoodItemsDesc(String foodItemsDesc) {
		this.foodItemsDesc = foodItemsDesc;
	}
	public Long getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialId(Long rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}
	public String getRawMaterialDesc() {
		return rawMaterialDesc;
	}
	public void setRawMaterialDesc(String rawMaterialDesc) {
		this.rawMaterialDesc = rawMaterialDesc;
	}
	/*public Long getUnitsId() {
		return unitsId;
	}
	public void setUnitsId(Long unitsId) {
		this.unitsId = unitsId;
	}
	public String getUnitsDesc() {
		return unitsDesc;
	}
	public void setUnitsDesc(String unitsDesc) {
		this.unitsDesc = unitsDesc;
	}
	*/public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
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

}
