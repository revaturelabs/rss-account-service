package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AccountTypeDAO;
import com.revature.entity.AccountType;
import com.revature.entity.User;

@Service
public class AccountTypeService {

	private AccountTypeDAO acctypedao;
	
	@Autowired
	public AccountTypeService(AccountTypeDAO acctypedao) {
		this.acctypedao = acctypedao;
	}

	public List<AccountType> getAllAccTypes() {
		return acctypedao.findAll();
	}
	
	public AccountType addAccountType(AccountType accType) {
		return acctypedao.save(accType);
	}
	
	public AccountType findById(int accTypeId) {

        return this.acctypedao.findAccTypeByAccTypeId(accTypeId);
    }
	
}
