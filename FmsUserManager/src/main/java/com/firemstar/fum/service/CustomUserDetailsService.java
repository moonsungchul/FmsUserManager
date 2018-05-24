package com.firemstar.fum.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> load user by username :  " + name);
		TUser uu = userDAO.getByUser(name);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> load user TUser : " + uu);
		if(uu != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String passwd = passwordEncoder.encode(uu.getPassword()).toString();
			SimpleUserDetail detail = new SimpleUserDetail(uu.getUserId(), passwd, uu.getRoles());
			logger.info(">>>>>> simple user detail : " + detail );
			return detail;
		}
		logger.info(">>>>>> errror user : " + uu);
		throw new UsernameNotFoundException("No user found for username " + name);
	}
}
