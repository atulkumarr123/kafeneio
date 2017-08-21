package com.kafeneio.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ORDERS")
public class orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	
	@Column(name="order_no")
	private String order_no;

	@Column(name="order_desc")
	private String order_desc;
	
	@Column(name="order_creation_timestamp")
	private Date orderCreationTimestamp;
	
	 public Date getOrderCreationTimestamp() {
		return orderCreationTimestamp;
	}

	public void setOrderCreationTimestamp(Date orderCreationTimestamp) {
		this.orderCreationTimestamp = orderCreationTimestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrder_desc() {
		return order_desc;
	}

	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
	}


	public Set<kafeneioOrdersDetails> getKafeneioOrdersDetails() {
		return kafeneioOrdersDetails;
	}

	public void setKafeneioOrdersDetails(Set<kafeneioOrdersDetails> kafeneioOrdersDetails) {
		this.kafeneioOrdersDetails = kafeneioOrdersDetails;
	}

	@OneToMany(mappedBy="orders", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @Column(nullable = true)
	 @JsonManagedReference
	 private Set<kafeneioOrdersDetails> kafeneioOrdersDetails;
	
	

}
