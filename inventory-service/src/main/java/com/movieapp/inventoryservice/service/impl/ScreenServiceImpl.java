package com.movieapp.inventoryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.ScreenService;

@Service
@Transactional
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	TheatreRepository theatreRepository;

	public static final String ERROR_CONNECT="Failed to connect";
	/**
	 * @author Mahendran Dayalan
	 * @param screen
	 * @return Screen
	 * @throws ServiceException
	 * 
	 * To add screen details to database
	 */
	@Override
	public Screen addScreen(Screen screen) throws ServiceException {
		try {
			Theatre theatre = theatreRepository.getOne(screen.getTheatre().getId());
			screen.setTheatre(theatre);
			return screenRepository.save(screen);
		} catch (DataAccessException e) {
			throw new ServiceException("Screen Not Saved", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @return List<Screen>
	 * @throws ApplicationException
	 * 
	 * To retrieve all screen details in database
	 */
	@Override
	public List<Screen> getAllScreen() throws ApplicationException {
		try {
			List<Screen> screenList = screenRepository.findAll();
			if (screenList.isEmpty()) {
				throw new ApplicationException("Screen Not Found");
			}
			return screenList;
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param screen
	 * @return Screen
	 * @throws ServiceException
	 * 
	 * Update a particular screen detail
	 */
	@Override
	public Screen updateScreen(Screen screen) throws ServiceException {
		try {
			Theatre theatre = theatreRepository.getOne(screen.getTheatre().getId());
			screen.setTheatre(theatre);
			return screenRepository.save(screen);
		} catch (DataAccessException e) {
			throw new ServiceException("Screen Not Updated", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param screenId
	 * @throws ServiceException
	 * 
	 * Delete screen based on screen id
	 */
	@Override
	public void deleteScreen(int screenId) throws ServiceException {
		try {
		screenRepository.deleteById(screenId);
		}catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param screenId
	 * @return Screen
	 * @throws ApplicationException
	 * 
	 * Get a screen detail based on screen id
	 */
	@Override
	public Screen getScreenById(int screenId) throws ApplicationException {
		try {
			return screenRepository.findById(screenId)
					.orElseThrow(() -> new ApplicationException("Screen Not Found for the ID" + " " + screenId));
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

}
