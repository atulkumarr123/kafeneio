package com.kafeneio.model;
import java.util.Date;

public class ExpensesDto {

	private Long id;
	private Double amount;
	private String remarks;
 	private Long expenseType;
	private String item;
	private String creationDate;
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

	public Long getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(Long expenseType) {
		this.expenseType = expenseType;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}