package com.movieapp.inventoryservice.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Screen implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5088098603196751996L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int totalSeats;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;

	public Screen(int id, String name, int totalSeats, Theatre theatre) {
		super();
		this.id = id;
		this.name = name;
		this.totalSeats = totalSeats;
		this.theatre = theatre;
	}

	public Screen(String name, int totalSeats,  Theatre theatre) {
		super();
		this.name = name;
		this.totalSeats = totalSeats;
		this.theatre = theatre;
	}

	public Screen() {
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

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	


}
