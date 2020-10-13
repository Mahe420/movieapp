package com.movieapp.inventoryservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Theatre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8198428148116859972L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToOne
	private Location location;
	@OneToOne
	private Address address;
	private int noofScreens;
	public Theatre() {
		super();
	}
	public Theatre(String name, Location location, Address address, int noofScreens) {
		super();
		this.name = name;
		this.location = location;
		this.address = address;
		this.noofScreens = noofScreens;
	}
	public Theatre(int id, String name, Location location, Address address, int noofScreens) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.address = address;
		this.noofScreens = noofScreens;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getNoofScreens() {
		return noofScreens;
	}
	public void setNoofScreens(int noofScreens) {
		this.noofScreens = noofScreens;
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", location=" + location + ", address=" + address
				+ ", noofScreens=" + noofScreens + "]";
	}
	
	
}
