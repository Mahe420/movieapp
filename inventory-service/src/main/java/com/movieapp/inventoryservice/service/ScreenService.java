package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;

public interface ScreenService {

	Screen addScreen(Screen screen) throws ServiceException;

	List<Screen> getAllScreen() throws ApplicationException;

	Screen updateScreen(Screen screen) throws ServiceException;

	void deleteScreen(int screenId) throws ServiceException;

	Screen getScreenById(int screenId) throws  ApplicationException;

}
