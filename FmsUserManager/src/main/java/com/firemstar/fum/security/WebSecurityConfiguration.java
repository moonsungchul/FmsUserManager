package com.firemstar.fum.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.firemstar.fum.repository.UserDAO;
import com.firemstar.fum.service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("security logger ...");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/**").permitAll();
	
		/*
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		
		http.formLogin()
			.permitAll()
			.loginProcessingUrl("/user/login")
			.successHandler(authSuccessHandler)
			.failureHandler(authFailureHandler)
			.and()
			.logout()
			.permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessHandler(logoutSuccessHandler)
			.and()
			.sessionManagement()
			.maximumSessions(1);
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/version").permitAll()
			.anyRequest().authenticated();
			*/
			
	}
	
}
