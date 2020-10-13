package com.movieapp.bookingservice.dto;

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

}
