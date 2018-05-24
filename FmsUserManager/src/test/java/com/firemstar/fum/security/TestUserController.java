package com.firemstar.fum.security;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.hasItem; 
import static org.hamcrest.CoreMatchers.is; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.SpringApplicationConfiguration; 
import org.springframework.http.MediaType; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import org.springframework.test.context.web.WebAppConfiguration; 
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.test.web.servlet.setup.MockMvcBuilders; 
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firemstar.fum.db.model.TUser; 


@RunWith(SpringJUnit4ClassRunner.class) 
//@SpringApplicationConfiguration(classes = ExampleApplication.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class TestUserController {
	
	private Logger logger = LoggerFactory.getLogger(TestUserController.class);
	
	@Autowired 
	private WebApplicationContext wac; 
	private MockMvc mvc;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		TUser request = new TUser(); 
		request.setUserId("moonsuchstar1@firemstar.com");
		request.setPassword("wooag01");
		ObjectMapper om = new ObjectMapper(); 
		try {
			mvc.perform(post("/user/login")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(om.writeValueAsString(request)))
					.andExpect(status().isOk());
					//.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
					//.andExpect(jsonPath("$.username", is(request.getName().toUpperCase())))
					//.andExpect(jsonPath("$.authorities[*].authority", hasItem("USER"))) ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.info("json processing exception : " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(" exception : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
