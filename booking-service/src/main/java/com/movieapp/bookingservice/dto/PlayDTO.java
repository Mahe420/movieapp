package com.movieapp.bookingservice.dto;

import java.io.Serializable;

public class PlayDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String startTiming;
    private  String endTiming;
    private int availableSeats;
    private MovieDTO movie;
    private ScreenDTO screen;
    private String date;
	public PlayDTO() {
		super();
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
	public MovieDTO getMovie() {
		return movie;
	}
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	public ScreenDTO getScreen() {
		return screen;
	}
	public void setScreen(ScreenDTO screen) {
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
	public PlayDTO(int id, String startTiming, String endTiming, int availableSeats, MovieDTO movie, ScreenDTO screen,
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
	public PlayDTO(String startTiming, String endTiming, int availableSeats, MovieDTO movie, ScreenDTO screen,
			String date) {
		super();
		this.startTiming = startTiming;
		this.endTiming = endTiming;
		this.availableSeats = availableSeats;
		this.movie = movie;
		this.screen = screen;
		this.date = date;
	}
	
	

}
