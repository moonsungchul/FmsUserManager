package com.firemstar.fum.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.firemstar.fum.db.model.TUser;
import com.firemstar.fum.db.model.TUserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityMember extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	public SecurityMember(TUser member) {
		this.setName(member.getUserId());
		this.setPassword(member.getPassword());
		this.setRoles(makeGrantedAuthority(member.getRoles()));
	}

	private static List<String> makeGrantedAuthority(List<TUserRole> roles) {
		List<String> list = new ArrayList<>();
		roles.forEach(role -> 
			list.add(ROLE_PREFIX + role.getRoleName()));
		return list;
	}


}
