package com.kafeneio.model;

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
		private String orderNo;
		@Column(name="amount")
		private String amount;
		
		@Column(name="creation_date")
		private String creation_date;
		

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


		public String getOrderNo() {
			return orderNo;
		}


		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}


		public String getAmount() {
			return amount;
		}


		public void setAmount(String amount) {
			this.amount = amount;
		}


		public String getCreation_date() {
			return creation_date;
		}


		public void setCreation_date(String creation_date) {
			this.creation_date = creation_date;
		}


		public Set<OrderDetails> getOrderDetails() {
			return orderDetails;
		}


		public void setOrderDetails(Set<OrderDetails> orderDetails) {
			this.orderDetails = orderDetails;
		}
}
