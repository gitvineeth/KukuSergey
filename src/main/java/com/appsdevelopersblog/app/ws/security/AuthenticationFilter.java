package com.appsdevelopersblog.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdevelopersblog.app.ws.SpringApplicationContext;
import com.appsdevelopersblog.app.ws.service.UserService;
import com.appsdevelopersblog.app.ws.shared.dto.UserDto;
import com.appsdevelopersblog.app.ws.ui.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// below code populates UserLoginRequestModel
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
	           //db lookup loadUserByemail etc happen here internally
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>())
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
		  // We use JWT to give a token to the user who are authenticated so that he can use it in the subsequent requests
		// need to use this in the request header
		 String userName = ((User) authResult.getPrincipal()).getUsername(); 
	     String token = Jwts.builder()
	    		        .setSubject(userName)
	    		        .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
	    		        .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
	    		        .compact();
	     
	     UserService userservice = (UserService) SpringApplicationContext.getBean("userServiceImpl");
	     
	     UserDto userDto = userservice.getUser(userName);
	     
	     response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
	     response.addHeader("Public User ID", userDto.getUserId());
	    		        
		 // above is used to set a header field and value and send along with response
	     //next time add this value to the request header
		 
	}

}
