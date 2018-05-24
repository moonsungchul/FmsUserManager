package com.firemstar.fum.beans;

import java.util.List;

import com.firemstar.fum.db.model.TUser;

public class UserList {
	
	private long total;
	private long page;
	private long page_volume;
	private List<TUser> users;
	
	public UserList() {
		
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPage_volume() {
		return page_volume;
	}

	public void setPage_volume(long page_volume) {
		this.page_volume = page_volume;
	}

	public List<TUser> getUsers() {
		return users;
	}

	public void setUsers(List<TUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserList [total=" + total + ", page=" + page + ", page_volume=" + page_volume + ", users=" + users
				+ "]";
	}
	
	
	

}
