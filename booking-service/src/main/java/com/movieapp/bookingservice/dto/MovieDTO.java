package com.movieapp.bookingservice.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class MovieDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9179315364199139575L;
	private int id;
	private String name;
	private String title;
	private List<CastDTO> cast;
	private int duration;
	private List<GenreDTO> genre;
	private String synopsis;
	private String facts;
	private byte[] trailer;
	private String language;
	private Date releaseDate;
	private byte[] picture;
	private int frequentView;
	
	public MovieDTO() {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<CastDTO> getCast() {
		return cast;
	}
	public void setCast(List<CastDTO> cast) {
		this.cast = cast;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<GenreDTO> getGenre() {
		return genre;
	}
	public void setGenre(List<GenreDTO> genre) {
		this.genre = genre;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getFacts() {
		return facts;
	}
	public void setFacts(String facts) {
		this.facts = facts;
	}
	public byte[] getTrailer() {
		return trailer;
	}
	public void setTrailer(byte[] trailer) {
		this.trailer = trailer;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public int getFrequentView() {
		return frequentView;
	}
	public void setFrequentView(int frequentView) {
		this.frequentView = frequentView;
	}
	public MovieDTO(int id, String name, String title, List<CastDTO> cast, int duration, List<GenreDTO> genre,
			String synopsis, String facts, byte[] trailer, String language, Date releaseDate, byte[] picture,
			int frequentView) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.cast = cast;
		this.duration = duration;
		this.genre = genre;
		this.synopsis = synopsis;
		this.facts = facts;
		this.trailer = trailer;
		this.language = language;
		this.releaseDate = releaseDate;
		this.picture = picture;
		this.frequentView = frequentView;
	}
	public MovieDTO(String name, String title, List<CastDTO> cast, int duration, List<GenreDTO> genre, String synopsis,
			String facts, byte[] trailer, String language, Date releaseDate, byte[] picture, int frequentView) {
		super();
		this.name = name;
		this.title = title;
		this.cast = cast;
		this.duration = duration;
		this.genre = genre;
		this.synopsis = synopsis;
		this.facts = facts;
		this.trailer = trailer;
		this.language = language;
		this.releaseDate = releaseDate;
		this.picture = picture;
		this.frequentView = frequentView;
	}
	
}
