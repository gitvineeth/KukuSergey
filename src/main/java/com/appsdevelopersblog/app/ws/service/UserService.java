package com.appsdevelopersblog.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appsdevelopersblog.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto findByUserId(String userId);
	UserDto updateUser(UserDto user,String id) throws Exception;
	void deleteUser(String userId);
	List<UserDto> getUsersList(int page, int limit);

}
