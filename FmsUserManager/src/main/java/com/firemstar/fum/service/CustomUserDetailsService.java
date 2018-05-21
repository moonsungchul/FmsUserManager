package com.firemstar.fum.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.firemstar.fum.db.model.TUser;
import com.firemstar.fum.repository.UserDAO;
import com.firemstar.fum.security.SimpleUserDetail;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	UserDAO userDAO ;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> load user by username ");
		TUser uu = userDAO.getByUser(name);
		if(uu != null) {
			SimpleUserDetail detail = new SimpleUserDetail(uu.getUserId(), uu.getPassword(), uu.getRoles());
			return detail;
		}
		throw new UsernameNotFoundException("No user found for username " + name);
	}
}
