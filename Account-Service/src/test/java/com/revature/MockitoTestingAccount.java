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
import com.revature.service.AccountService;

@SpringBootTest
public class MockitoTestingAccount {

	private static Account account;
	private static User user;
	private static AccountType accountType;
	
	 AccountService accountService = Mockito.mock(AccountService.class);

	@BeforeClass
	public void initialize() throws Exception {
		user = new User(1, "email@email.com", "password", null, "firstname", "lastname", false);
		accountType = new AccountType("test");
		account = new Account(1, 1, 1, 0);
	}
	
	@Test
	void getAllUserTest() {
		when(accountService.getAllUsers()).thenReturn(Stream
				.of(account).collect(Collectors.toList()));
		assertEquals(1,accountService.getAllUsers().size());
	}
	
	@Test 
	void addAccountTest() {
		
		when(accountService.addAccount(account)).thenReturn(account);
		assertEquals(account,accountService.addAccount(account));
		}
	
	@Test
	void userByAccountIdTest() {
		Account account1 = accountService.findById(1);	
		assertEquals(account,account1);
	}
	
	@Test
	void getAccountByUserIdTest() {
		when(accountService.findAccountById(1)).thenReturn(Stream
				.of(account).collect(Collectors.toList()));
		assertEquals(1,accountService.findAccountById(1).size());
	}
	
}
