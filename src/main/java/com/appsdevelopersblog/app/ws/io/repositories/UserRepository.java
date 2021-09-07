package com.appsdevelopersblog.app.ws.io.repositories;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appsdevelopersblog.app.ws.io.entity.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	//UserEntity findByFirstName(String firstName);
	UserEntity findByUserId(String userId);
	

}
