package com.appsdevelopersblog.app.ws.shared.dto;

public class AddressDTO {

private long id;
private String city;
private String country;
private String streetName;
private String postalCode;
private String type;
private UserDto userDetails;

public AddressDTO(long id, String city, String country, String streetName, String postalCode, String type,
		UserDto userDetails) {
	super();
	this.id = id;
	this.city = city;
	this.country = country;
	this.streetName = streetName;
	this.postalCode = postalCode;
	this.type = type;
	this.userDetails = userDetails;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getStreetName() {
	return streetName;
}
public void setStreetName(String streetName) {
	this.streetName = streetName;
}
public String getPostalCode() {
	return postalCode;
}
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public UserDto getUserDetails() {
	return userDetails;
}
public void setUserDetails(UserDto userDetails) {
	this.userDetails = userDetails;
}


}
