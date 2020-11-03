package com.movieapp.userservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movieapp.userservice.dto.UserDTO;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.exception.ApplicationException;
import com.movieapp.userservice.exception.ServiceException;
import com.movieapp.userservice.repository.UserRepository;
import com.movieapp.userservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UserServiceImpl.class)
public class UserServiceImplTest {


	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;
	
	@Test
	public void addUserTest() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
		User user=userService.addUser(getUser());
		assertEquals(1, user.getId());
		
	}


	@Test(expected=ApplicationException.class)
	public void addUserTestError() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(Mockito.mock(DataAccessException.class));
		User user=userService.addUser(getUser());
	}

	@Test
	public void getAllUserTest() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(getUser());
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		List<UserDTO> userList1=userService.getAllUser();
		assertEquals(1,userList1.size());
		
	}


	@Test(expected=ApplicationException.class)
	public void getAllUserTestError() throws Exception {
		Mockito.when(userRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<UserDTO> userList1=userService.getAllUser();
	}

	@Test
	public void getUserByIdTest() throws Exception {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getUser()));
		UserDTO user=userService.getUserById(1);
		assertEquals(1,user.getId());
	}

	

	@Test(expected=ApplicationException.class)
	public void getUserByIdTestError() throws Exception {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		UserDTO user=userService.getUserById(1);
	}

	@Test
	public void updateUserTest() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
		User user=userService.updateUser(getUser());
		assertEquals(1,user.getId());
		
}

	@Test(expected=ServiceException.class)
	public void updateUserTestError() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(Mockito.mock(DataAccessException.class));
		User user=userService.updateUser(getUser());
	}

	@Test
	public void deleteUserTest() throws Exception {
		userService.deleteUser(1);
		Mockito.verify(userRepository,Mockito.times(1)).deleteById(Mockito.any());
	}
	
	@Test(expected=ServiceException.class)
	public void deleteUserException() throws Exception{
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(userRepository).deleteById(Mockito.anyInt());
		userService.deleteUser(1);
	}

	public User getUser() {
		User user = new User();
		user.setId(1);
		user.setFirstName("mahe");
		user.setLastName("daya");
		user.setEmail("mahe@gmail.com");
		user.setPhoneNumber("9876543212");
		user.setUserName("mahe123");
		user.setPassword("$2b$10$O9SvMvtmbfCFH2E.XVUQMeAk.rZ6u0u/akO6YRgCgYaoOhb7x1zT2");
		user.setRole("admin");
		return user;
	}
}
