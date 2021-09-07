package com.appsdevelopersblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdevelopersblog.app.ws.exceptions.UserServiceExceptions;
import com.appsdevelopersblog.app.ws.io.entity.UserEntity;
import com.appsdevelopersblog.app.ws.io.repositories.UserRepository;
import com.appsdevelopersblog.app.ws.service.UserService;
import com.appsdevelopersblog.app.ws.shared.Utils;
import com.appsdevelopersblog.app.ws.shared.dto.UserDto;
import com.appsdevelopersblog.app.ws.ui.request.UserLoginRequestModel;
import com.appsdevelopersblog.app.ws.ui.response.ErrorMessages;

import org.springframework.security.core.userdetails.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(userDto, userEntity);

//		for (int i = 0; i < userDto.getAddresses().size(); i++) {
//			AddressDto address = userDto.getAddresses().get(i);
//			address.setUserDetails(userDto);
//			address.setAddressId(utils.generateAddressId(30));
//			userDto.getAddresses().set(i, address);
//		}
		BeanUtils.copyProperties(userDto, userEntity);
//		System.out.println(userEntity.getAddresses());
		// userEntity.setUserId("test");
		String publicUserId = utils.generateUserId(30);
	
		userEntity.setUserId(publicUserId);

		System.out.println(publicUserId);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new RuntimeException("Record already exixts");

		// System.out.println("through userrepo method name
		// is"+userRepository.findByFirstName(userDto.getFirstName()));

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		// Spring core userDetails package
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>()); // User is a class
																										// that
																										// implements
		// User is a class that implements UserDetail

		// Gets user password and username and send it back
		// Spring will use this username and password to check if it can see this in the
		// database

	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new RuntimeException(email);
		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto findByUserId(String userId) {
		UserDto userDto = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new RuntimeException(userId);
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto user, String id) throws Exception {
		UserEntity currentUser = userRepository.findByUserId(id);
		if (currentUser == null)
			throw new UserServiceExceptions(ErrorMessages.EMPTY_OBJECT.getErrorMessage());
// do later//		currentUser.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		UserEntity updatedUser = userRepository.save(currentUser);
		UserDto backtoController = new UserDto();
		BeanUtils.copyProperties(updatedUser, backtoController);
		return backtoController;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity currentUser = userRepository.findByUserId(userId);
		if (currentUser == null)
			throw new RuntimeException("Record does not exist");
		userRepository.delete(currentUser);

	}

	@Override
	public List<UserDto> getUsersList(int page, int limit) {
		List<UserDto> users = new ArrayList<>();
		Pageable pageablerequest = PageRequest.of(page, limit);
		Page<UserEntity> usersfromdb = userRepository.findAll(pageablerequest);
		List<UserEntity> usersfetched = usersfromdb.getContent();
		for (UserEntity userss : usersfetched) {

			UserDto userdto = new UserDto();
			BeanUtils.copyProperties(userss, userdto);
			users.add(userdto);

		}
		return users;

	}

}
