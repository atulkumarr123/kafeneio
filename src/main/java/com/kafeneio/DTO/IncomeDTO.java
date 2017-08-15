package com.kafeneio.DTO;

public class IncomeDTO {
	private Long id;
	private Long userId;
	private Long referralId;
	private Boolean status;
	private String neftNumber;
	private String date;
	private String IncomeType;
	private String name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getReferralId() {
		return referralId;
	}
	public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getNeftNumber() {
		return neftNumber;
	}
	public void setNeftNumber(String neftNumber) {
		this.neftNumber = neftNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncomeType() {
		return IncomeType;
	}
	public void setIncomeType(String incomeType) {
		IncomeType = incomeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
