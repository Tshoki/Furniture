package com.furniture_rental.dto;

public class UserInfoDTO {
private int id;
private String UserId;
private String password;
private String familyName;
private String firstName;
private String familyNameKana;
private String firstNameKana;
private int sex;
private String email;
private int logined;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserId() {
	return UserId;
}
public void setUserId(String userId) {
	UserId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFamilyName() {
	return familyName;
}
public void setFamilyName(String familyName) {
	this.familyName = familyName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getFamilyNameKana() {
	return familyNameKana;
}
public void setFamilyNameKana(String familyNameKana) {
	this.familyNameKana = familyNameKana;
}
public String getFirstNameKana() {
	return firstNameKana;
}
public void setFirstNameKana(String firstNameKana) {
	this.firstNameKana = firstNameKana;
}
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getLogined() {
	return logined;
}
public void setLogined(int logined) {
	this.logined = logined;
}

}
