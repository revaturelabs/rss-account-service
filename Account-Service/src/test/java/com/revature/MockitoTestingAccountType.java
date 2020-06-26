package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeClass;

import com.revature.entity.Account;
import com.revature.entity.AccountType;
import com.revature.entity.User;
import com.revature.service.AccountTypeService;

@SpringBootTest
public class MockitoTestingAccountType {

	private static Account account;
	private static User user;
	private static AccountType accountType;
	
	 AccountTypeService atService = Mockito.mock(AccountTypeService.class);

	@BeforeClass
	public void initialize() throws Exception {
		user = new User(1, "email@email.com", "password", null, "firstname", "lastname", false);
		accountType = new AccountType("test");
		account = new Account(1, 1, 1, 0);
	}

	@Test
	void getAllAccTypesTest() {
		when(atService.getAllAccTypes()).thenReturn(Stream
				.of(accountType).collect(Collectors.toList()));
		
		assertEquals(1, atService.getAllAccTypes().size());
	}
	
	@Test 
	void addAccTypesTest() {
		
		when(atService.addAccountType(accountType)).thenReturn(accountType);
		assertEquals(accountType,atService.addAccountType(accountType));
		}

}
