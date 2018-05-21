package com.firemstar.fum.controller;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firemstar.fum.beans.RetMsg;
import com.firemstar.fum.db.model.AccessLog;
import com.firemstar.fum.db.model.TUser;
import com.firemstar.fum.exception.BaseException;
import com.firemstar.fum.repository.UserDAO;

@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	
	@RequestMapping(value="/user/version", method=RequestMethod.GET)
	@ResponseBody	
	public String getVersion() {	
		return "FmsUsrManager version 0.1";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public TUser createUser(@RequestBody TUser user) {
		logger.info(">>>>>> create user : " + user.toString());
		if(user == null)
			throw new BaseException("base exception"); 
		if(user.getUserId().isEmpty()) throw new BaseException("userId is empty...");
		if(user.getName().isEmpty()) throw new BaseException("name is empty...");
		if(user.getEmail().isEmpty()) throw new BaseException("email is empty ...");
		if(user.getPassword().isEmpty()) throw new BaseException("password is empty ...");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwd = passwordEncoder.encode(user.getPassword()).toString();
		user.setPassword(passwd);
		user.setAccessToken("");
		user.setCreateDate(new Date());
		user.setModifiedDate(new Date());
		logger.info("create user : " + user.toString());
		userDAO.create(user);
		return user;
		
	}
	
	
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	@ResponseBody
	public TUser logout(@RequestBody TUser user) {
		if(user == null)
			throw new BaseException("base exception"); 
		if(user.getUserId().isEmpty()) throw new BaseException("userId is empty...");
		if(user.getPassword().isEmpty()) throw new BaseException("password is empty...");
		
		TUser user2 = this.userDAO.getByAccessToken(user.getUserId(), user.getAccessToken());
		if(user2 == null) throw new BaseException("user is not found");
		
		AccessLog accessLog = this.userDAO.getByAccessLog(user2.getUserId());
		if(accessLog == null) throw new BaseException("access log if not found");
		accessLog.setLogoutTime(new Date());
		user2.setAccessToken("");
		
		
		userDAO.update(user2);
		userDAO.updateAccessLog(accessLog);
		
		user2.setPassword("");
		return user2;
		
	}
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public TUser login(@RequestBody TUser user) {
		if(user == null)
			throw new BaseException("base exception"); 
		
		if(user.getUserId().isEmpty()) throw new BaseException("userId is empty...");
		if(user.getPassword().isEmpty()) throw new BaseException("password is empty...");
		
		TUser user2 = this.userDAO.getByUser(user.getUserId(), user.getPassword());
		if(user2 == null) throw new BaseException("user is not found");
		
		user2.setAccessToken(UUID.randomUUID().toString());
		user2.setModifiedDate(new Date());
		
		
		AccessLog log = new AccessLog();
		log.setUserId(user2.getUserId());
		log.setLoginTime(new Date());
		log.setLogoutTime(new Date());
		
		this.userDAO.createAccessLog(log);
		this.userDAO.update(user2);
		
		return user2;
	}
	
	
	
	

}
