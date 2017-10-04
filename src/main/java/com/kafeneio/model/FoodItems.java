package com.kafeneio.model;

import java.util.Comparator;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="KAFENEIO_FOOD_ITEMS")
public class FoodItems{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	@Column(name="status")
	private boolean status;
	@Column(name="creation_date")
	private Date date;
	
	@Column(unique = true,name="food_item_code")
	private String foodItemCode;
	
	@Column(name="food_item_desc")
	private String foodItemDesc;

	/*@ManyToOne
	@JoinColumn(name="category_id")*/
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    @JsonBackReference
 	private FoodCategory foodCategory;
	@Column(name="amount") 
	private int amount;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public FoodCategory getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
