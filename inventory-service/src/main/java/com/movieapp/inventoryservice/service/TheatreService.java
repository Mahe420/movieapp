package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.exception.TheatreNotFoundException;

public interface TheatreService {

	Theatre addTheatre(Theatre theatre) throws ServiceException;

	List<Theatre> getAlltheatre() throws TheatreNotFoundException;

	Theatre updateTheatre(Theatre theatre) throws ServiceException;

	void deleteTheatre(int theatreId) throws ServiceException;

	Theatre getTheatreById(int theatreId) throws TheatreNotFoundException;

}
