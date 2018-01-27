package com.kafeneio.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItemsDto{
	private Long id;
	private boolean status;
	private String statusVisible;	
	private Date date;
	private String foodItemCode;
	private String foodItemDesc;
 	private Long foodCategoryId;
 	private String foodCategoryDesc;
 	private BigDecimal amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFoodItemCode() {
		return foodItemCode;
	}
	public void setFoodItemCode(String foodItemCode) {
		this.foodItemCode = foodItemCode;
	}
	public String getFoodItemDesc() {
		return foodItemDesc;
	}
	public void setFoodItemDesc(String foodItemDesc) {
		this.foodItemDesc = foodItemDesc;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getFoodCategoryId() {
		return foodCategoryId;
	}
	public void setFoodCategoryId(Long foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}
	public String getFoodCategoryDesc() {
		return foodCategoryDesc;
	}
	public void setFoodCategoryDesc(String foodCategoryDesc) {
		this.foodCategoryDesc = foodCategoryDesc;
	}
	public String getStatusVisible() {
		return statusVisible;
	}
	public void setStatusVisible(String statusVisible) {
		this.statusVisible = statusVisible;
	}

	

}
