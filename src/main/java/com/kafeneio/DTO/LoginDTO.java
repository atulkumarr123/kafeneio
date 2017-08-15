package com.kafeneio.DTO;

public class LoginDTO {

	private String email;
	private String password;
	private Boolean isRememberMe;
	private String role;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean isRememberMe() {
		return isRememberMe;
	}
	public void setRememberMe(Boolean isRememberMe) {
		this.isRememberMe = isRememberMe;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
