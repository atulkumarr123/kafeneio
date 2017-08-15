package com.kafeneio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class LoginModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	@Column(name="role")
	private String role="USER";
	
	public Long getId() {
		return id;
	}

	/*public void setId(Long id) {
		this.id = id;
	}*/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
