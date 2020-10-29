package com.movieapp.userservice.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@Pattern(regexp="^[a-zA-Z]{1,30}+$", message="Invalid FirstName")
	@NotNull
	private String firstName;
	@Pattern(regexp="^[a-zA-Z]{1,30}+$", message="Invalid LastName")
	@NotNull
	private String lastName;
	@NotNull
	@Pattern(regexp= "([a-zA-Z]{1}[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[\\.][a-z]+)",
	message="Invalid Email")
	private String email;
	@Pattern(regexp="[6-9]{1}[0-9]{9}", message="Invalid PhoneNumber")
	private String phoneNumber;
	@NotNull
	private String userName;
	@NotNull
	@Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[!@#$%^&*])\\S{8,}\\z",message="Invalid Password")
	private String password;
	@NotNull
	private String role;
	public UserDTO() {
		super();
	}
	public UserDTO(int id,@Pattern(regexp = "^[a-zA-Z]{1,30}+$", message = "Invalid FirstName") @NotNull String firstName,
			@Pattern(regexp = "^[a-zA-Z]{1,30}+$", message = "Invalid LastName") @NotNull String lastName,
			@NotNull @Pattern(regexp = "([a-zA-Z]{1}[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[\\.][a-z]+)", message = "Invalid Email") String email,
			@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Invalid PhoneNumber") String phoneNumber,
			@NotNull String userName,
			@NotNull @Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[!@#$%^&*])\\S{8,}\\z", message = "Invalid Password") String password,
			@NotNull String role) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
