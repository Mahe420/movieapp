package com.movieapp.notificationservice.dto;

import java.io.Serializable;

public class Booking  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5740553537468539343L;
	private int id;
	private double totalPrice;
	private int userid;
	private int playid;
	private int noOfBookedSeats;
	
	public Booking() {
		super();
	}
	
	public int getUserid() {
		return userid;
	}
	

	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPlayid() {
		return playid;
	}
	public void setPlayid(int playid) {
		this.playid = playid;
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

	public int getNoOfBookedSeats() {
		return noOfBookedSeats;
	}

	public void setNoOfBookedSeats(int noOfBookedSeats) {
		this.noOfBookedSeats = noOfBookedSeats;
	}

	public Booking(int id, double totalPrice, int userid, int playid, int noOfBookedSeats) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.userid = userid;
		this.playid = playid;
		this.noOfBookedSeats = noOfBookedSeats;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", totalPrice=" + totalPrice + ", userid=" + userid + ", playid=" + playid
				+ ", noOfBookedSeats=" + noOfBookedSeats + "]";
	}
	
	

	
	

}
