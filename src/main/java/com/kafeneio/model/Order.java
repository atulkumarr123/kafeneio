package com.kafeneio.model;

import java.util.Date;
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
@Table(name="KAFENEIO_ORDER")
public class Order {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id" ,unique=true,nullable=false )
		private Long id;
		@Column(name="order_no")
		private Long orderNo;
		@Column(name="amount")
		private Double amount;
		
		@Column(name="creation_date")
		private Date creation_date;
		

		 @OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		 @Column(nullable = true)
		 @JsonManagedReference
		 private Set<OrderDetails> orderDetails ;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Long getOrderNo() {
			return orderNo;
		}


		public void setOrderNo(Long orderNo) {
			this.orderNo = orderNo;
		}


		public Double getAmount() {
			return amount;
		}


		public void setAmount(Double amount) {
			this.amount = amount;
		}


		public Date getCreation_date() {
			return creation_date;
		}


		public void setCreation_date(Date creation_date) {
			this.creation_date = creation_date;
		}


		public Set<OrderDetails> getOrderDetails() {
			return orderDetails;
		}


		public void setOrderDetails(Set<OrderDetails> orderDetails) {
			this.orderDetails = orderDetails;
		}
}
