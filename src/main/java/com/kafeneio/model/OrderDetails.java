package com.kafeneio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;	
import javax.persistence.Table;


@Entity
@Table(name="KAFENEIO_ORDER_DETAILS")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	
	@Column(name="FOOD_CODE")
	private String foodCode;
	
	@Column(name="FOOD_DESC")
	private String foodDesc;
	
	@Column(name="QUANTITY") 
	private int quantity;
	
	@Column(name="AMOUNT") 
	private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodCode() {
		return foodCode;
	}

	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}

	public String getFoodDesc() {
		return foodDesc;
	}

	public void setFoodDesc(String foodDesc) {
		this.foodDesc = foodDesc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

}
