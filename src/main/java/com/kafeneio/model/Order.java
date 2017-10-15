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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kafeneio.constants.ApplicationConstant;

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
		@Column(name="table_no")
		private String table;
		@JsonFormat(pattern = ApplicationConstant.DATE_FORMAT)
		@Column(name="creation_date")
		private Date creationDate;
		
		@OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@Column(nullable = true)
		@JsonManagedReference
		private Set<OrderDetails> orderDetails ;
		 
		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinColumn(name="status")
	    @JsonBackReference
	 	private OrderStatus status;

		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    @JoinColumn(name="mode_of_payment")
	    @JsonBackReference
	 	private ModeOfPayment modeOfPayment ;

		
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

		public Set<OrderDetails> getOrderDetails() {
			return orderDetails;
		}

		public void setOrderDetails(Set<OrderDetails> orderDetails) {
			this.orderDetails = orderDetails;
		}

		public Date getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public String getTable() {
			return table;
		}

		public void setTable(String table) {
			this.table = table;
		}

		public ModeOfPayment getModeOfPayment() {
			return modeOfPayment;
		}

		public void setModeOfPayment(ModeOfPayment modeOfPayment) {
			this.modeOfPayment = modeOfPayment;
		}

}
