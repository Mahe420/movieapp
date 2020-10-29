package com.movieapp.notificationservice.dto;

import java.io.Serializable;

public class ScreenDTO implements Serializable {
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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((theatre == null) ? 0 : theatre.hashCode());
		result = prime * result + totalSeats;
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
		ScreenDTO other = (ScreenDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (theatre == null) {
			if (other.theatre != null)
				return false;
		} else if (!theatre.equals(other.theatre))
			return false;
		if (totalSeats != other.totalSeats)
			return false;
		return true;
	}


}
