package com.movieapp.notificationservice.dto;

public class AddressDTO {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((flatNo == null) ? 0 : flatNo.hashCode());
		result = prime * result + id;
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		AddressDTO other = (AddressDTO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (flatNo == null) {
			if (other.flatNo != null)
				return false;
		} else if (!flatNo.equals(other.flatNo))
			return false;
		if (id != other.id)
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", flatNo=" + flatNo + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}

	

}
