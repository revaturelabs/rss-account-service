package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AccountDAO;
import com.revature.entity.Account;

@Service
public class AccountService {

	private AccountDAO accdao;
	
	@Autowired
	public AccountService(AccountDAO accdao) {
		this.accdao = accdao;
	}

    public List<Account> getAllUsers() {
        return this.accdao.findAll();
    }

    public Account addAccount(Account acc) {
        return this.accdao.save(acc);
    }

    
    public Account findById(int accId) {
    	return this.accdao.getOne(accId);
    }
    
    public List<Account> findAccountById(int userId) {
    	return this.accdao.findAccountByUserId(userId);
    }
    
}
