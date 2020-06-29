package com.revature;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;

import com.revature.entity.User;
import com.revature.service.UserService;

@SpringBootTest
public class MockitoTestingUser {
	
	private static User user;
	
	 UserService userService = Mockito.mock(UserService.class);

	@BeforeClass
	public void initialize() throws Exception {
		user = new User(1, "email@email.com", "password", null, "firstname", "lastname", false);
	}
	
	@Test
	void getAllUserTest() {
		when(userService.getAllUsers()).thenReturn(Stream
				.of(user).collect(Collectors.toList()));
		
		assertEquals(1,userService.getAllUsers().size());
	}
	
	@Test 
	void addUserTest() {
		
		when(userService.addUser(user)).thenReturn(user);
		assertEquals(user,userService.addUser(user));
		}
	
	@Test
	void existsByEmailTest() {
		
		when(userService.existsByEmail("email@email.com")).thenReturn(true);
		
		Boolean a = userService.existsByEmail("email@email.com");
		assertTrue(a);
		
		Boolean b = userService.existsByEmail("sdfdsf");
		assertFalse(b);
	}
	
	@Test
	void userByEmailTest() {
		User user1 = userService.findUserByEmail("email@email.com");
		//when(userService.findUserByEmail(user.getEmail())).thenReturn(user);
		
		assertEquals(user,user1);
	}
	
	@Test
	void userByUserIdTest() {
		User user1 = userService.findById(1);
		//when(userService.findUserByEmail(user.getEmail())).thenReturn(user);
		
		assertEquals(user,user1);
	}
	
	
	
}