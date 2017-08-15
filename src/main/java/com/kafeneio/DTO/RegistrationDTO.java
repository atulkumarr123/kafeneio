package com.kafeneio.DTO;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;



public class RegistrationDTO  {
	
	private static final long serialVersionUID = -740730286795643372L;
	private Long id;
	private String name;
	
	private String contact;
	
	private String panCardFlag;
	private String panCard;
	private String nominee;
	private String sponsorId;
	private String sponsorName;
	private String position;
	private String planType;
	private LoginDTO login;
	private String termsAndCondition;
	private byte[] profileImage;
	//private Long parentId;
	//private ParentChildDTO  parentChild;
	
	 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	public String getPanCardFlag() {
		return panCardFlag;
	}
	public void setPanCardFlag(String panCardFlag) {
		this.panCardFlag = panCardFlag;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getNominee() {
		return nominee;
	}
	public void setNominee(String nominee) {
		this.nominee = nominee;
	}
	public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	public String getTermsAndCondition() {
		return termsAndCondition;
	}
	public void setTermsAndCondition(String termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}
	public LoginDTO getLogin() {
		return login;
	}
	public void setLogin(LoginDTO login) {
		this.login = login;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	
	
	
}
