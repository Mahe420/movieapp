package com.movieapp.moviesearch.dto;

import java.util.Arrays;

public class Cast {
	private int id;
    private String name;
    private String roll;
    private byte[] picture;
	public Cast() {
		super();
	}
	public Cast(String name, String roll, byte[] picture) {
		super();
		this.name = name;
		this.roll= roll;
		this.picture = picture;
	}
	public Cast(int id, String name, String roll, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.roll= roll;
		this.picture = picture;
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
	@Override
	public String toString() {
		return "Cast [id=" + id + ", name=" + name + ", role=" + roll + ", picture=" + Arrays.toString(picture) + "]";
	}

	
}
