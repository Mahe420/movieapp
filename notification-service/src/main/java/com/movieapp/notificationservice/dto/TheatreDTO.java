package com.movieapp.notificationservice.dto;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + noofScreens;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheatreDTO other = (TheatreDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noofScreens != other.noofScreens)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TheatreDTO [id=" + id + ", name=" + name + ", location=" + location + ", address=" + address
				+ ", noofScreens=" + noofScreens + "]";
	}
	
	
}
