package com.movieapp.notificationservice.dto;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class MovieDTO {

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result + ((facts == null) ? 0 : facts.hashCode());
		result = prime * result + frequentView;
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(picture);
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((synopsis == null) ? 0 : synopsis.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + Arrays.hashCode(trailer);
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
		MovieDTO other = (MovieDTO) obj;
		if (duration != other.duration)
			return false;
		if (facts == null) {
			if (other.facts != null)
				return false;
		} else if (!facts.equals(other.facts))
			return false;
		if (frequentView != other.frequentView)
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(picture, other.picture))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (synopsis == null) {
			if (other.synopsis != null)
				return false;
		} else if (!synopsis.equals(other.synopsis))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (!Arrays.equals(trailer, other.trailer))
			return false;
		return true;
	}
	
}
