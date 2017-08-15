package com.kafeneio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paymentDetail")
public class PaymentModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id" ,unique=true,nullable=false) 
    private Long id;
	private Long user_id;
	private String userName;
	private float binaryIncome;
	private int completeBinary;
	private float directReferralIncome;
	private float workAssigmentIncome;
	private float totalIncome;
	private float totalPayoutIncome;
	private float tds;
	private float adminTax;
	private Boolean status;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getBinaryIncome() {
		return binaryIncome;
	}
	public void setBinaryIncome(float binaryIncome) {
		this.binaryIncome = binaryIncome;
	}
	public int getCompleteBinary() {
		return completeBinary;
	}
	public void setCompleteBinary(int completeBinary) {
		this.completeBinary = completeBinary;
	}
	public float getDirectReferralIncome() {
		return directReferralIncome;
	}
	public void setDirectReferralIncome(float directReferralIncome) {
		this.directReferralIncome = directReferralIncome;
	}
	public float getWorkAssigmentIncome() {
		return workAssigmentIncome;
	}
	public void setWorkAssigmentIncome(float workAssigmentIncome) {
		this.workAssigmentIncome = workAssigmentIncome;
	}
	public float getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}
	public float getTotalPayoutIncome() {
		return totalPayoutIncome;
	}
	public void setTotalPayoutIncome(float totalPayoutIncome) {
		this.totalPayoutIncome = totalPayoutIncome;
	}
	public float getTds() {
		return tds;
	}
	public void setTds(float tds) {
		this.tds = tds;
	}
	public float getAdminTax() {
		return adminTax;
	}
	public void setAdminTax(float adminTax) {
		this.adminTax = adminTax;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
