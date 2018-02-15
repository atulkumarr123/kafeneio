package com.kafeneio.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private BigDecimal amount;

	@Column
	@JsonIgnore
	@OneToMany(targetEntity=Inventory.class, mappedBy="foodItems", fetch=FetchType.EAGER)
	private List<Inventory> inventory ;

	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public List<Inventory> getInventory() {
		return inventory;
	}
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}
	
	
	
	
}
