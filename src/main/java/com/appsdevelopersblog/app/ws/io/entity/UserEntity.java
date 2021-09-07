package com.appsdevelopersblog.app.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;




@Entity(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -981207773439376109L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	public String firstName;

	@Column(nullable = false, length = 50)
	public String lastName;

	@Email
	@Column(nullable = false, length = 120, unique = true)
	public String email;
	@Column(nullable = false)
	public String userId;
	@Column(nullable = false)
	public String encryptedPassword;

	@Column(nullable = false)
	public Boolean emailVerificationStatus = false;
	public String emailVerificationToken;
	
	
	
	
	
public UserEntity() {
		super();
	}

public UserEntity(long id, String firstName, String lastName, @Email String email, String userId,
			String encryptedPassword, Boolean emailVerificationStatus, String emailVerificationToken
			) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userId = userId;
		this.encryptedPassword = encryptedPassword;
		this.emailVerificationStatus = emailVerificationStatus;
		this.emailVerificationToken = emailVerificationToken;
	
	}

//////	
//	

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
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
