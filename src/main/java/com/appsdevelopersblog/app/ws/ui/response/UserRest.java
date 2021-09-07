package com.appsdevelopersblog.app.ws.ui.response;

public class UserRest {
	
	public String firstName;
	public String lastName;
	public String email;
	public String userId;
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
	public UserRest(String firstName, String lastName, String email, String userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userId = userId;
	}
	public UserRest() {
		super();
	}

	
}
