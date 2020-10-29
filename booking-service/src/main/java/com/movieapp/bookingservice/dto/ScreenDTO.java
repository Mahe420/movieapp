package com.movieapp.bookingservice.dto;

import java.io.Serializable;

public class ScreenDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int totalSeats;
	private TheatreDTO theatre;

	public ScreenDTO(int id, String name, int totalSeats, TheatreDTO theatre) {
		super();
		this.id = id;
		this.name = name;
		this.totalSeats = totalSeats;
		this.theatre = theatre;
	}

	public ScreenDTO(String name, int totalSeats, TheatreDTO theatre) {
		super();
		this.name = name;
		this.totalSeats = totalSeats;
		this.theatre = theatre;
	}

	public ScreenDTO() {
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

	public TheatreDTO getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreDTO theatre) {
		this.theatre = theatre;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	



}
