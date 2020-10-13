package com.movieapp.bookingservice.dto;

public class BookingDTO {
	private int id;
	private double totalPrice;
	private int noOfBookedSeats;
	private UserDTO user;
	private PlayDTO play;
	
	public BookingDTO() {
	}
	
	public BookingDTO(int id, double totalPrice, int noOfBookedSeats, UserDTO user, PlayDTO play) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.noOfBookedSeats = noOfBookedSeats;
		this.user = user;
		this.play = play;
	}

	public int getNoOfBookedSeats() {
		return noOfBookedSeats;
	}

	public void setNoOfBookedSeats(int noOfBookedSeats) {
		this.noOfBookedSeats = noOfBookedSeats;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public PlayDTO getPlay() {
		return play;
	}
	public void setPlay(PlayDTO play) {
		this.play = play;
	}
}
