package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;

public interface PlayService {

	Play addPlay(Play play) throws ServiceException;

	List<Play> getAllPlay() throws  ApplicationException;

	Play updatePlay(Play play) throws ServiceException;

	void deletePlay(int playId) throws ServiceException;

	Play getPlayById(int playId) throws ApplicationException;

}
