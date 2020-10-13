package com.movieapp.inventoryservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -543243004928803364L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String flatNo;
	private String street;
	private String city;
	private String state;
	private String pincode;

	public Address() {
		super();
	}

	public Address(String flatNo, String street, String city, String state, String pincode) {
		super();
		this.flatNo = flatNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public Address(int id, String flatNo, String street, String city, String state, String pincode) {
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
