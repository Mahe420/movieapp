package com.movieapp.inventoryservice.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cast implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8277164213149067971L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private String name;
    private String roll;
    @Lob
    private byte[] picture;
	public Cast() {
		super();
	}
	public Cast(String name, String roll, byte[] picture) {
		super();
		this.name = name;
		this.roll = roll;
		this.picture = picture;
	}
	public Cast(int id, String name, String roll, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.roll = roll;
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
		return "Cast [id=" + id + ", name=" + name + ", roll=" + roll + ", picture=" + Arrays.toString(picture) + "]";
	}
	
}
