package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;

public interface TheatreService {

	Theatre addTheatre(Theatre theatre) throws ServiceException;

	List<Theatre> getAlltheatre() throws ApplicationException;

	Theatre updateTheatre(Theatre theatre) throws ServiceException;

	void deleteTheatre(int theatreId) throws ServiceException;

	Theatre getTheatreById(int theatreId) throws ApplicationException;

}
