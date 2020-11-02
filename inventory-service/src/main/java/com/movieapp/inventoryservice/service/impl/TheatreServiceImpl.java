package com.movieapp.inventoryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.inventoryservice.entity.Address;
import com.movieapp.inventoryservice.entity.Location;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.AddressRepository;
import com.movieapp.inventoryservice.repository.LocationRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.TheatreService;

@Service
@Transactional
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	TheatreRepository theatreRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	LocationRepository locationRepository;

	/**
	 * @author Mahendran Dayalan
	 * @param theatre
	 * @return Theatre
	 * @throws ServiceException
	 * 
	 * Add theatre details to database
	 */
	@Override
	public Theatre addTheatre(Theatre theatre) throws ServiceException {
		try {
			Address address = theatre.getAddress();
			addressRepository.save(address);
			Location location = theatre.getLocation();
			locationRepository.save(location);
			return theatreRepository.save(theatre);
		} catch (DataAccessException e) {
			throw new ServiceException("Theatre Not Saved", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @return List<Theatre>
	 * @throws ApplicationException
	 * 
	 * Get all theatre details
	 */
	@Override
	public List<Theatre> getAlltheatre() throws ApplicationException {
		try {
			List<Theatre> theatreList  = theatreRepository.findAll();
			if (theatreList.isEmpty()) {
				throw new ApplicationException("No Theatre Found");
			}
			return theatreList;
		} catch (DataAccessException e) {
			throw new ServiceException("Failed to connect", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param theatre
	 * @return Theatre
	 * @throws ServiceException
	 * 
	 * Update a particular theatre detail
	 */
	@Override
	public Theatre updateTheatre(Theatre theatre) throws ServiceException {
		try {
			Address address = theatre.getAddress();
			addressRepository.save(address);
			Location location = theatre.getLocation();
			locationRepository.save(location);
			return theatreRepository.save(theatre);
		} catch (DataAccessException e) {
			throw new ServiceException("Theatre Not Updated", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param theatreId
	 * @throws ServiceException
	 * 
	 * delete theatre based on theatre id
	 */
	@Override
	public void deleteTheatre(int theatreId) throws ServiceException {
		try {
		theatreRepository.deleteById(theatreId);
		}catch(DataAccessException e) {
			throw new ServiceException("Theatre Not Updated", e.getCause());
		}
	}

	/**
	 * @author Mahendran Dayalan
	 * @param theatreId
	 * @return Theatre
	 * @throws ApplicationException
	 * 
	 * Get theatre based on theatre id
	 */
	@Override
	public Theatre getTheatreById(int theatreId) throws ApplicationException {
		try {
			return theatreRepository.findById(theatreId)
					.orElseThrow(() -> new ApplicationException("No Theatre Found for the ID" + " " + theatreId));
			
		} catch (DataAccessException e) {
			throw new ServiceException("Failed to connect", e.getCause());
		}
	}

}
