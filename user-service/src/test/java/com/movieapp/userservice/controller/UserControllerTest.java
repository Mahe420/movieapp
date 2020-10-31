package com.movieapp.userservice.controller;


import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.userservice.config.AuthorizationServerConfiguration;
import com.movieapp.userservice.controller.UserController;
import com.movieapp.userservice.entity.User;
import com.movieapp.userservice.repository.UserRepository;
import com.movieapp.userservice.service.UserService;
import com.movieapp.userservice.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest({ UserController.class, UserServiceImpl.class, AuthorizationServerConfiguration.class })
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@Test
	public void addUserTest() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
		MvcResult result = mockMvc.perform(post("/users/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.content(getUserAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("mahe"));
	}

	@Test
	public void UserNameAlreadyExistsTest() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.findUserByuserName(Mockito.anyString())).thenReturn(Optional.of(getUser()));
		MvcResult result = mockMvc.perform(post("/users/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.content(getUserAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Username Already Exists"));
	}

	@Test
	public void addUserTestError() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(post("/users/v1/signup").contentType(MediaType.APPLICATION_JSON)
				.content(getUserAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("User Not Saved"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getAllUserTest() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(getUser());
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		MvcResult result = this.mockMvc.perform(get("/users/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("mahe"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getAllUserNotFoundTest() throws Exception {
		List<User> userList = new ArrayList<>();
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		MvcResult result = this.mockMvc.perform(get("/users/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No User Found"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getAllUserTestError() throws Exception {
		Mockito.when(userRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/users/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getUserByIdTest() throws Exception {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getUser()));
		MvcResult result = mockMvc.perform(get("/users/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("mahe"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getUserByIdNotFoundTest() throws Exception {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		MvcResult result = mockMvc.perform(get("/users/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No User Found for the ID 1"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "ADMIN" })
	public void getUserByIdTestError() throws Exception {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/users/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "USER" })
	public void updateUserTest() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
		MvcResult result = mockMvc.perform(put("/users/v1").contentType(MediaType.APPLICATION_JSON)
				.content(getUserAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("mahe"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "USER" })
	public void updateUserTestError() throws JsonProcessingException, Exception {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(put("/users/v1").contentType(MediaType.APPLICATION_JSON)
				.content(getUserAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("User Not Updated"));
	}

	@Test
	@WithMockUser(username = "mahe", roles = { "USER" })
	public void deleteUserTest() throws Exception {
		MvcResult result = this.mockMvc.perform(delete("/users/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("User Deleted Successfull"));
	}

	public User getUser() {
		User user = new User();
		user.setFirstName("mahe");
		user.setLastName("daya");
		user.setEmail("mahe@gmail.com");
		user.setPhoneNumber("9876543212");
		user.setUserName("mahe123");
		user.setPassword("$2b$10$O9SvMvtmbfCFH2E.XVUQMeAk.rZ6u0u/akO6YRgCgYaoOhb7x1zT2");
		user.setRole("admin");
		return user;
	}

	public String getUserAsJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getUser());
	}
}
