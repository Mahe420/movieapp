package com.movieapp.inventoryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.repository.PlayRepository;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.service.PlayService;

@Service
@Transactional
public class PlayServiceImpl implements PlayService {

	public static final String ERROR_CONNECT="Failed to connect";
	@Autowired
	PlayRepository playRepository;

	@Autowired
	ScreenRepository screenRepository;

	@Autowired
	MovieRepository movieRepository;

	/**
	 * @author Mahendran Dayalan
	 * @param play
	 * @return Play
	 * @throws ServiceException
	 * 
	 * Add play details to database
	 */
	@Override
	public Play addPlay(Play play) throws ServiceException {
		try {
			Screen screen = screenRepository.getOne(play.getScreen().getId());
			Movie movie = movieRepository.getOne(play.getMovie().getId());
			play.setScreen(screen);
			play.setMovie(movie);
			return playRepository.save(play);
		} catch (DataAccessException e) {
			throw new ServiceException("Play Not Saved", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @return List<Play>
	 * @throws ApplicationException
	 * 
	 * Get all play details present in database
	 */
	
	@Override
	public List<Play> getAllPlay() throws ApplicationException {
		try {
			List<Play> playList = playRepository.findAll();
			if (playList.isEmpty()) {
				throw new ApplicationException("No Play Found");
			}
			return playList;
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param play
	 * @return Play
	 * @throws ServiceException
	 * 
	 * Update a play detail present in database
	 */
	@Override
	public Play updatePlay(Play play) throws ServiceException {
		try {
			Screen screen = screenRepository.getOne(play.getScreen().getId());
			Movie movie = movieRepository.getOne(play.getMovie().getId());
			play.setScreen(screen);
			play.setMovie(movie);
			return playRepository.save(play);
		} catch (DataAccessException e) {
			throw new ServiceException("Play Not Updated", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param playId
	 * @throws ServiceException
	 * 
	 * delete play using id
	 */
	@Override
	public void deletePlay(int playId) throws ServiceException {
		try {
			playRepository.deleteById(playId);
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param playId
	 * @return Play
	 * @throws ApplicationException
	 * 
	 * get play detail using play id
	 */
	@Override
	public Play getPlayById(int playId) throws ApplicationException {
		try {
			return playRepository.findById(playId)
					.orElseThrow(() -> new ApplicationException("No Play Found for he ID" + " " + playId));
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

}
