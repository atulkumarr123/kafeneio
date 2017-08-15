package com.kafeneio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="income")
public class IncomeModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false )
	private Long id;
	@Column(name="user_id")
	private Long userId;
	@Column(name="referral_id")
	private Long referralId;
	@Column(name="status")
	private Boolean status;
	@Column(name="neft_number")
	private String neftNumber;
	@Column(name="date")
	private String date;
	@Column(name="income_type")
	private String incomeType;
	
	@Column(name="node")
	private String node;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	

}
