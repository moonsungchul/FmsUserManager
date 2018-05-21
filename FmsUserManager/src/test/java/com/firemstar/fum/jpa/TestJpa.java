package com.firemstar.fum.jpa;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.test.context.junit4.SpringRunner;

import com.firemstar.fum.db.model.AccessLog;
import com.firemstar.fum.db.model.TUser;
import com.firemstar.fum.db.model.TUserRole;
import com.firemstar.fum.repository.UserDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJpa {
	
	private Logger logger = LoggerFactory.getLogger(TestJpa.class);
	
	@Autowired
	private UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//@Test
	public void test2() {
		AccessLog tokens = this.userDAO.getByAccessLog("moonsuchstar@firemstar.com");
		logger.info("tokens : " + tokens);
		/*
		for(AccessLog log : tokens) {
			logger.info(log.toString());
		} */
	}

	@Test
	public void test() {
		logger.info(">>>>>>>> test ok <<<<<<<<");
		TUser user = new TUser();
		user.setUserId("moonsuchstar@firemstar.com");
		user.setName("moonstar");
		user.setEmail("moonsuchstar@firemstar.com");
		user.setPassword("wooag01");
		user.setCreateDate(new Date());
		user.setModifiedDate(new Date());
		user.setAccessToken(UUID.randomUUID().toString());
		
		TUserRole role = new TUserRole("BASIC");
		user.getRoles().add(role); 
		logger.info(">>>>> user : " + user.toString());
		this.userDAO.create(user);
	}

}
