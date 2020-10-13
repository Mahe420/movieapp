package com.movieapp.userservice.service;

import java.util.List;

import com.movieapp.userservice.dto.UserDTO;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.exception.ServiceException;
import com.movieapp.userservice.exception.UserNameAlreadyExistsException;
import com.movieapp.userservice.exception.UserNotFoundException;

public interface UserService {

	User addUser(User user) throws UserNameAlreadyExistsException ;

	List<UserDTO> getAllUser() throws UserNotFoundException;

	UserDTO getUserById(int userId) throws UserNotFoundException;

	void deleteUser(int userId) throws ServiceException;

	User updateUser(User user) throws ServiceException;

}
