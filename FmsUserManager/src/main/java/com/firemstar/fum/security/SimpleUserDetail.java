package com.firemstar.fum.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.firemstar.fum.db.model.TUserRole;

@SuppressWarnings("serial")
public class SimpleUserDetail implements UserDetails {
	
	private static final long serialVersionUID = -4910129348688612117L;
		private String username;
		private String password;
		private boolean enabled = true;
		private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		public SimpleUserDetail(String username, String pw, List<TUserRole> exroles) {
			this.username = username;
			this.password = pw;
			
			for(TUserRole r : exroles) {
				authorities.add(new SimpleGrantedAuthority(role(r.getRoleName())));
			}

		}
		
		public String toString() {
			return "{enabled:" + isEnabled() + ", username:'" + getUsername() + "', password:'" + getPassword() + "'}";
		}

		@Override
		public boolean isEnabled() {
			return this.enabled;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return this.enabled;
		}

		@Override
		public boolean isAccountNonLocked() {
			return this.enabled;
		}

		@Override
		public boolean isAccountNonExpired() {
			return this.enabled;
		}

		@Override
		public String getUsername() {
			return this.username;
		}

		@Override
		public String getPassword() {
			return this.password;
		}

		private String role(String i) {
			return "ROLE_" + i;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.authorities;
		}

}
