package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AccountDAO;

@Service
public class AccountService {

	private AccountDAO accdao;
	
	@Autowired
	public AccountService(AccountDAO accdao) {
		this.accdao = accdao;
	}

	

}
