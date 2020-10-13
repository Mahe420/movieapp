package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.exception.PlayNotFoundException;
import com.movieapp.inventoryservice.exception.ServiceException;

public interface PlayService {

	Play addPlay(Play play) throws ServiceException;

	List<Play> getAllPlay() throws PlayNotFoundException;

	Play updatePlay(Play play) throws ServiceException;

	void deletePlay(int playId) throws ServiceException;

	Play getPlayById(int playId) throws PlayNotFoundException;

}
