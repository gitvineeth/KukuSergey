
package com.appsdevelopersblog.app.ws.ui.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdevelopersblog.app.ws.exceptions.UserServiceExceptions;
import com.appsdevelopersblog.app.ws.io.entity.UserEntity;
import com.appsdevelopersblog.app.ws.service.UserService;
import com.appsdevelopersblog.app.ws.shared.dto.UserDto;
import com.appsdevelopersblog.app.ws.ui.request.UserDetailsRequestModel;
import com.appsdevelopersblog.app.ws.ui.response.ErrorMessages;
import com.appsdevelopersblog.app.ws.ui.response.OperationStatusModel;
import com.appsdevelopersblog.app.ws.ui.response.RequestOperationName;
import com.appsdevelopersblog.app.ws.ui.response.RequestOperationStatus;
import com.appsdevelopersblog.app.ws.ui.response.UserRest;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserDto userDto = new UserDto();
		UserRest userRest = new UserRest();
	    userDto = userService.findByUserId(id);
	    BeanUtils.copyProperties(userDto, userRest);
		return userRest;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest addUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel ) throws Exception
	{
		
		if(userDetailsRequestModel.getFirstName().isEmpty()) throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		
//		UserDto userDto=new UserDto();
//		BeanUtils.copyProperties(userDetailsRequestModel,userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetailsRequestModel, UserDto.class);
		UserDto createdUser=userService.createUser(userDto);
		UserRest returnValue= modelMapper.map(createdUser, UserRest.class);
//		BeanUtils.copyProperties(createdUser, returnValue);
		
		
		System.out.println(userDetailsRequestModel.getFirstName()+"firstName");
		return returnValue;
	}
	
	
	@PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public UserRest UpdateUser(@PathVariable String id,@RequestBody UserDetailsRequestModel userdetails) throws Exception
	{   UserRest response = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userdetails,userDto);
		UserDto updatedUser =  userService.updateUser(userDto,id);
		BeanUtils.copyProperties(updatedUser,response);
		return response;
	}
	
	@DeleteMapping(path = "/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public OperationStatusModel DeleteUser(@PathVariable String userId)
	{ 
		OperationStatusModel opr = new OperationStatusModel();
		opr.setOperationName(RequestOperationName.DELETE.name());
		opr.setOperationResult(RequestOperationStatus.SUCCESS.name());
		userService.deleteUser(userId);
		
		
		
		return opr;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit" ,defaultValue = "50") int limit)
	{
		List<UserRest> userslist = new ArrayList<>();
		List<UserDto> users = userService.getUsersList(page,limit);
		for(Object o : users) {
			UserRest unit = new UserRest();
			BeanUtils.copyProperties(o,unit);
			userslist.add(unit);
		}
		return userslist;
		
	}
	

}
