package com.movieapp.userservice.service;

import java.util.List;

import com.movieapp.userservice.dto.UserDTO;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.exception.ApplicationException;
import com.movieapp.userservice.exception.ServiceException;

public interface UserService {

	User addUser(User user) throws  ApplicationException ;

	List<UserDTO> getAllUser() throws ApplicationException;

	UserDTO getUserById(int userId) throws ApplicationException;

	void deleteUser(int userId) throws ServiceException;

	User updateUser(User user) throws ServiceException;

}
