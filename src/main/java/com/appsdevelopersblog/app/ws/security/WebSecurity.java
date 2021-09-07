package com.appsdevelopersblog.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdevelopersblog.app.ws.service.UserService;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	// first this item will be populated
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//authentication() method gives an object of type Authentication
		/*
		 * http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,
		 * SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated().and()
		 * .addFilter(new AuthenticationFilter(authenticationManager()));
		 * 
		 */
		
		
		 http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,
				 SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated().and()
	 .addFilter(getAuthenticationFilter())
						 .addFilter( new AuthorizationFilter(authenticationManager()))
						 .sessionManagement()
					 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				  
				
		
		
		
	}
	
	@Override // next this item will be populated
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
	  final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
//	  filter.setFilterProcessesUrl("/users/login");
	  return filter;
	}
}
