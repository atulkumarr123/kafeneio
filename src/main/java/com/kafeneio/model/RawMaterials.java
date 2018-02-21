package com.kafeneio.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
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


@Entity
@Table(name="RAW_MATERIALS")
public class RawMaterials extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;

	@Column(unique = true,name="raw_material_code")
	private String rawMaterialCode;

	@Column(name="raw_material_desc")
	private String rawMaterialDesc;
	
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstant.DATE_FORMAT)
	@Column(name="CREATION_DATE")
	private Date creationDate;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstant.DATE_FORMAT)
	@Column(name="last_updated_date")
	private Date lastUpdatedDate;
*/	
	@Column(name="quantity")
	private BigDecimal quantity;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="lower_limit")
	private BigDecimal lowerLimit;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="units_id")
    @JsonBackReference
 	private Units unit;

	@Column
	@OneToMany(targetEntity=InventoryRules.class, mappedBy="rawMaterial", fetch=FetchType.EAGER)
	private List<InventoryRules> inventory;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
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
	
	public List<InventoryRules> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryRules> inventory) {
		this.inventory = inventory;
	}

	
}
