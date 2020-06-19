package com.revature;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.entity.User;
import com.revature.service.UserService;

public class MockitoTesting {
	
	
	 UserService userService = Mockito.mock(UserService.class);

	@Test 
	void addUserTest() {
		
		final User user = new User("Test@gmail.com","password","ahmad","shiekh");
		when(userService.addUser(user)).thenReturn(user);
		
		assertEquals(user,userService.addUser(user));
		}
	
	@Test
	void userByEmailTest() {
		final User user = new User("kidx@gmail.com");
	
		when(userService.findUserByEmail(user.getEmail())).thenReturn(user);
		
		assertEquals(user,userService.findUserByEmail(user.getEmail()));
	}
	
}
