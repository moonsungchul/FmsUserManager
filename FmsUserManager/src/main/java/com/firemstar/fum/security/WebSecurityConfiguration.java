package com.firemstar.fum.security;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
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
	public BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	} 

	/*
	@Bean
	public static PasswordEncoder passwordEncoder() {
	      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 } */

	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
	    // This is the ID we use for encoding.
	    String currentId = "pbkdf2.2018";

	    // List of all encoders we support. Old ones still need to be here for rolling updates
	    Map<String, PasswordEncoder> encoders = new HashMap<>();
	    encoders.put("bcrypt", new BCryptPasswordEncoder());
	    encoders.put(currentId, new Pbkdf2PasswordEncoder(PBKDF2_2018_SECRET, PBKDF2_2018_ITERATIONS, PBKDF2_2018_HASH_WIDTH));

	    return new DelegatingPasswordEncoder(currentId, encoders);
	}*/
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	} 
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	   return super.authenticationManagerBean();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("security logger ...");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/**").permitAll();
		http.authorizeRequests().antMatchers("/**").permitAll();
		
		http.authorizeRequests()
			.antMatchers("/user/login").permitAll()
			.antMatchers("/user").hasAuthority("USER")
			.antMatchers("/admin").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.logout();
		
		//http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
	
		/*
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
			*/
	
		/*
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/user/version").permitAll()
			.antMatchers(HttpMethod.GET, "/login").permitAll()
			//.antMatchers(HttpMethod.GET, "/users").permitAll()
			.anyRequest().authenticated()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/users").authenticated()
			.and().formLogin()
			.loginPage("/user/login")
			.loginProcessingUrl("/user/login")
			.defaultSuccessUrl("/");
			*/
		
	}
	
}
