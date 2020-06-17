package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AccountTypeDAO;

@Service
public class AccountTypeService {

	private AccountTypeDAO acctypedao;
	
	@Autowired
	public AccountTypeService(AccountTypeDAO acctypedao) {
		this.acctypedao = acctypedao;
	}
	
}
