package com.kafeneio.model;

import java.math.BigDecimal;

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
@Table(name = "Inventory_Rules")
public class InventoryRules extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "item_id")
	private FoodItems foodItems;

	 @ManyToOne
	 @JoinColumn(name = "rawmaterial_id")
	private RawMaterials rawMaterial;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="units_id")
 	private Units unit;

	@Column(name = "quantity")
	private BigDecimal quantity;

	@Column(name = "remarks")
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
   
    public FoodItems getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(FoodItems foodItems) {
		this.foodItems = foodItems;
	}
	
   
	public RawMaterials getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterials rawMaterial) {
		this.rawMaterial = rawMaterial;
	}


	public BigDecimal getQuantity() {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

}
