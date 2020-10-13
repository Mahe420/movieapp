package com.movieapp.inventoryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.inventoryservice.entity.Address;
import com.movieapp.inventoryservice.entity.Location;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.exception.TheatreNotFoundException;
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

	@Override
	public List<Theatre> getAlltheatre() throws TheatreNotFoundException {
		try {
			List<Theatre> theatreList  = theatreRepository.findAll();
			if (theatreList.isEmpty()) {
				throw new TheatreNotFoundException("No Theatre Found");
			}
			return theatreList;
		} catch (DataAccessException e) {
			throw new TheatreNotFoundException("Failed to connect", e.getCause());
		}
	}

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

	@Override
	public void deleteTheatre(int theatreId) throws ServiceException {
		try {
		theatreRepository.deleteById(theatreId);
		}catch(DataAccessException e) {
			throw new ServiceException("Theatre Not Updated", e.getCause());
		}
	}

	@Override
	public Theatre getTheatreById(int theatreId) throws TheatreNotFoundException {
		try {
			return theatreRepository.findById(theatreId)
					.orElseThrow(() -> new TheatreNotFoundException("No Theatre Found for the ID" + " " + theatreId));
			
		} catch (DataAccessException e) {
			throw new TheatreNotFoundException("Failed to connect", e.getCause());
		}
	}

}
