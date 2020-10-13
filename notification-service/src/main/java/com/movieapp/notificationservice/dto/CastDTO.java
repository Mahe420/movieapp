package com.movieapp.notificationservice.dto;

import java.util.Arrays;

public class CastDTO {

	private int id;
    private String name;
    private String roll;
    private byte[] picture;
    
	public CastDTO() {
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
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public CastDTO(int id, String name, String roll, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.roll = roll;
		this.picture = picture;
	}
	public CastDTO(String name, String roll, byte[] picture) {
		super();
		this.name = name;
		this.roll = roll;
		this.picture = picture;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(picture);
		result = prime * result + ((roll == null) ? 0 : roll.hashCode());
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
		CastDTO other = (CastDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(picture, other.picture))
			return false;
		if (roll == null) {
			if (other.roll != null)
				return false;
		} else if (!roll.equals(other.roll))
			return false;
		return true;
	}
    
}
