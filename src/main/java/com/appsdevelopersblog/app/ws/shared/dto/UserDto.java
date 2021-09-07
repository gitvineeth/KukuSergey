package com.appsdevelopersblog.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1559724561674796407L;
private long id;
private String firstName;
private String lastName;
private String email;
private String userId;
private String password;
private String encryptedPassword;





public UserDto(long id, String firstName, String lastName, String email, String userId, String password,
		String encryptedPassword,  Boolean emailVerificationStatus,
		String emailVerificationToken) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.userId = userId;
	this.password = password;
	this.encryptedPassword = encryptedPassword;

	this.emailVerificationStatus = emailVerificationStatus;
	this.emailVerificationToken = emailVerificationToken;
}


public UserDto() {
	super();
}


public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public  Boolean emailVerificationStatus=false;
public String emailVerificationToken;







public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEncryptedPassword() {
	return encryptedPassword;
}
public void setEncryptedPassword(String encryptedPassword) {
	this.encryptedPassword = encryptedPassword;
}
public Boolean getEmailVerificationStatus() {
	return (Boolean) emailVerificationStatus;
}
public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
	this.emailVerificationStatus = emailVerificationStatus;
}
public String getEmailVerificationToken() {
	return emailVerificationToken;
}
public void setEmailVerificationToken(String emailVerificationToken) {
	this.emailVerificationToken = emailVerificationToken;
}






	
}
