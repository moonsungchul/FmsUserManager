package com.firemstar.fum.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAccessLog")
public class AccessLog {
	

	@Id
	@GeneratedValue
	@Column(name="acid")
	private long acid;
	
	@Column(name="loginTime")
	private Date loginTime;
	
	@Column(name="logoutTime")
	private Date logoutTime;
	
	@Column(name="userId_TUser")
	private String userId;
	
	
	public AccessLog() {
		
	}


	public AccessLog(long acid, Date loginTime, Date logoutTime, String userId) {
		super();
		this.acid = acid;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.userId = userId;
	}


	public long getAcid() {
		return acid;
	}


	public void setAcid(long acid) {
		this.acid = acid;
	}


	public Date getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


	public Date getLogoutTime() {
		return logoutTime;
	}


	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "AccessLog [acid=" + acid + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + ", userId="
				+ userId + "]";
	}
	
	
	
	

}
