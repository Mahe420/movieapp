package com.movieapp.bookingservice.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String flatNo;
	private String street;
	private String city;
	private String state;
	private String pincode;

	public AddressDTO() {
		super();
	}

	public AddressDTO(String flatNo, String street, String city, String state, String pincode) {
		super();
		this.flatNo = flatNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public AddressDTO(int id, String flatNo, String street, String city, String state, String pincode) {
		super();
		this.id = id;
		this.flatNo = flatNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	


	@Override
	public String toString() {
		return "Address [id=" + id + ", flatNo=" + flatNo + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}

	

}
