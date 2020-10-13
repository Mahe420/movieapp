package com.movieapp.inventoryservice.entity;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Play implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6751515403355248599L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    private String startTiming;
    private  String endTiming;
	private int availableSeats;
    @OneToOne
    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="screen_id")
    private Screen screen;
    private String date;
	public Play() {
		super();
	}
	
	public Play(int id, String startTiming, String endTiming, int availableSeats, Movie movie, Screen screen,
			String date) {
		super();
		this.id = id;
		this.startTiming = startTiming;
		this.endTiming = endTiming;
		this.availableSeats = availableSeats;
		this.movie = movie;
		this.screen = screen;
		this.date = date;
	}
	

	public Play(String startTiming, String endTiming, int availableSeats, Movie movie, Screen screen, String date) {
		super();
		this.startTiming = startTiming;
		this.endTiming = endTiming;
		this.availableSeats = availableSeats;
		this.movie = movie;
		this.screen = screen;
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTiming() {
		return startTiming;
	}
	public void setStartTiming(String startTiming) {
		this.startTiming = startTiming;
	}
	public String getEndTiming() {
		return endTiming;
	}
	public void setEndTiming(String endTiming) {
		this.endTiming = endTiming;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	@Override
	public String toString() {
		return "Play [id=" + id + ", startTiming=" + startTiming + ", endTiming=" + endTiming + ", movie=" + movie
				+ ", screen=" + screen + ", date=" + date + "]";
	}
	
	
	
}
