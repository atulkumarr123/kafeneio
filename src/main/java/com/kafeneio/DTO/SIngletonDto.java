package com.kafeneio.DTO;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.utils.CustomDateDeserializer;

public class SIngletonDto {
		private Long id;
		private Long orderNo;
		private Double amount;
		private String table;
		private Date creationDate;	
		private Long discountPercentage;
		/*private Set<OrderDetails> orderDetails ;
	 	private OrderStatus status;
	 	private ModeOfPayment modeOfPayment ;*/

		
}
