package com.movieapp.userservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.userservice.dto.UserDTO;
import com.movieapp.userservice.entity.AuthUserDetails;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.exception.ApplicationException;
import com.movieapp.userservice.exception.ServiceException;
import com.movieapp.userservice.repository.UserRepository;
import com.movieapp.userservice.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	public static final String ERROR_CONNECT="Failed to connect";
	@Autowired
	private UserRepository userRepository;

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String name) {
		logger.info("To load user by name");
		User user=new User();
		user = userRepository.findUserByuserName(name)
				.orElseThrow(() -> new UsernameNotFoundException("UserName not found "));
		UserDetails userDetails = new AuthUserDetails(user);
		new AccountStatusUserDetailsChecker().check(userDetails);
		logger.info("User has been found");
		return userDetails;
	}

	@Override
	public User addUser(User user) throws ApplicationException {
		try {
			Optional<User> userDetails = userRepository.findUserByuserName(user.getUserName());
			if (userDetails.isPresent()) {
				logger.error("User is already present");
				throw new ApplicationException("Username Already Exists");
			}
			return userRepository.save(user);
		} catch (DataAccessException e) {
			logger.error("Error: {}", e.getCause());
			throw new ServiceException("User Not Saved", e.getCause());
		}
	}

	@Override
	public List<UserDTO> getAllUser() throws ApplicationException {
		try {
			List<User> userList = userRepository.findAll();
			if (userList.isEmpty()) {
				throw new ApplicationException("No User Found");
			}
			return userList.stream()
					.map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
							user.getPhoneNumber(), user.getUserName(), user.getPassword(), user.getRole()))
					.collect(Collectors.toList());
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	@Override
	public UserDTO getUserById(int userId) throws ApplicationException {
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new ApplicationException("No User Found for the ID" + " " + userId));
			return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
					user.getPhoneNumber(), user.getUserName(), user.getPassword(), user.getRole());
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	@Override
	public void deleteUser(int userId) throws ServiceException {
		try {
			userRepository.deleteById(userId);
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

	@Override
	public User updateUser(User user) throws ServiceException {
		try {
			return userRepository.save(user);
		} catch (DataAccessException e) {
			throw new ServiceException("User Not Updated", e.getCause());
		}
	}

}
