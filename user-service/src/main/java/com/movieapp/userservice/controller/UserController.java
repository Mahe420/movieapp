package com.movieapp.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.userservice.constants.Role;
import com.movieapp.userservice.dto.APISuccessResponseDTO;
import com.movieapp.userservice.dto.UserDTO;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.exception.ApplicationException;
import com.movieapp.userservice.exception.ServiceException;
import com.movieapp.userservice.service.UserService;

@RestController
@CrossOrigin
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping(value="/users/v1")
public class UserController {
	
	public  static final String MESSAGE="message";
	public static final int HTTP_STATUS_OK=200;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/signup")
	public ResponseEntity<APISuccessResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO,
			BindingResult bindingResult) throws ApplicationException {
		logger.info("Enter signup to add user");
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				errorMessage.append(error.getDefaultMessage() + " ");
			}
			logger.error("Error: {}", errorMessage);
			throw new ServiceException(errorMessage.toString());
		}
		String role;
		if (userDTO.getRole().equalsIgnoreCase("admin")) {
			role = Role.ROLE_ADMIN.name();
		} else {
			role = Role.ROLE_USER.name();
		}
		User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
				userDTO.getPhoneNumber(), userDTO.getUserName(), encoder.encode(userDTO.getPassword()), role);
		User userDetails = userService.addUser(user);
		APISuccessResponseDTO response =createResponse(userDetails, "User Saved Successfull");
		logger.info("Successfully added user");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping
	@PreAuthorize(" hasRole('ROLE_ADMIN')")
	public ResponseEntity<APISuccessResponseDTO> getAllUser() throws ApplicationException {
		logger.info("Entered getAll to retrive all users");
		List<UserDTO> userList = userService.getAllUser();
		APISuccessResponseDTO response =  createResponse(userList, "Get all User Successfull");
		logger.info("Successfully retrieved all users");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/{userId}")
	@PreAuthorize("  hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<APISuccessResponseDTO> getUserById(@PathVariable int userId) throws ApplicationException {
		logger.info("Entered to retrieve get user by id");
		UserDTO userDTO = userService.getUserById(userId);
		APISuccessResponseDTO response = createResponse(userDTO, "Get User by Id Successfull");
		logger.info("Successfully retrieve user by Id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@PutMapping
	@PreAuthorize(" hasRole('ROLE_USER')")
	public ResponseEntity<APISuccessResponseDTO> updateUser(@RequestBody User user) throws ServiceException {
		logger.info("Entered to update user");
		User userDetails = userService.updateUser(user);
		APISuccessResponseDTO response = createResponse(userDetails, "User Updated Successfull");
		logger.info("Successfully updated user");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<APISuccessResponseDTO> deleteUser(@PathVariable int userId) throws ServiceException {
		logger.info("Entered to delete user by id");
		
		userService.deleteUser(userId);
		APISuccessResponseDTO response = createResponse(null,"User Deleted Successfull");
		
		logger.info("Successfully deleted user");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	
	private APISuccessResponseDTO createResponse(Object object,String message) {
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(HTTP_STATUS_OK);
		response.setMessage(message);
		response.setBody(object);
		return response;
	}
}
