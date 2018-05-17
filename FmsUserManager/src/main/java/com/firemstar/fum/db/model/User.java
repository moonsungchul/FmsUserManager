package com.firemstar.fum.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TUser")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="userId")
	private String userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="accessToken")
	private String accessToken;
	
	@Column(name="createDate")
	private Date createDate;
	
	@Column(name="modifiedDate")
	private Date modifiedDate;
	
	public User() {
		
	}

	public User(String userId, String name, String password, String email, String accessToken, Date createDate,
			Date modifiedDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.accessToken = accessToken;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", accessToken=" + accessToken + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}
	
	
	
	
	

}
