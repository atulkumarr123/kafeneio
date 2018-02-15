package com.kafeneio.DTO;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kafeneio.constants.ApplicationConstant;

public class ExpensesDto {

	private Long id;
	private Double amount;
	private String remarks;
 	private Long expenseTypeId;
	private String item;
	private String expenseTypeDesc;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ApplicationConstant.DATE_FORMAT)
	private Date creationDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getExpenseTypeId() {
		return expenseTypeId;
	}
	public void setExpenseTypeId(Long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
	public String getExpenseTypeDesc() {
		return expenseTypeDesc;
	}
	public void setExpenseTypeDesc(String expenseTypeDesc) {
		this.expenseTypeDesc = expenseTypeDesc;
	}

	
}