package com.movieapp.bookingservice.dto;

public class GenreDTO {
	private int id;
	private String name;
	
	public GenreDTO() {}
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
	public GenreDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public GenreDTO(String name) {
		super();
		this.name = name;
	}

	
}
