package com.movieapp.bookingservice.dto;

public class TheatreDTO {

	private int id;
	private String name;
	private LocationDTO location;
	private AddressDTO address;
	private int noofScreens;
	
	public TheatreDTO() {
		super();
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
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	public int getNoofScreens() {
		return noofScreens;
	}
	public void setNoofScreens(int noofScreens) {
		this.noofScreens = noofScreens;
	}
	public TheatreDTO(String name, LocationDTO location, AddressDTO address, int noofScreens) {
		super();
		this.name = name;
		this.location = location;
		this.address = address;
		this.noofScreens = noofScreens;
	}
	public TheatreDTO(int id, String name, LocationDTO location, AddressDTO address, int noofScreens) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.address = address;
		this.noofScreens = noofScreens;
	}

	@Override
	public String toString() {
		return "TheatreDTO [id=" + id + ", name=" + name + ", location=" + location + ", address=" + address
				+ ", noofScreens=" + noofScreens + "]";
	}
	
	
}
