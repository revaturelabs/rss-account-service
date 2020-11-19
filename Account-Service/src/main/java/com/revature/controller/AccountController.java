package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Account;
import com.revature.entity.User;
import com.revature.service.AccountService;
import com.revature.util.Logging;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

	private AccountService accservice;

	@Autowired
	public AccountController(AccountService accservice) {
		this.accservice = accservice;
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;

//---------------Takes in the new account point total and saves it to database---------------

    @PutMapping(value="/points")
    public void updatePoints(@RequestBody Account acc) {
        Account a = this.accservice.findById(acc.getAccId());
        a.setPoints(acc.getPoints());
        Logging.Log4("info", a.getUserId() + " has updated their points");
        this.accservice.addAccount(a);
    }

  //---------------Will take the new points and add them to the account point total---------------

    @PutMapping(value="/points/a")
    public void addPoints(@RequestBody Account acc) {
        Account a = this.accservice.findById(acc.getAccId());
        a.setPoints(a.getPoints() + acc.getPoints());
        Logging.Log4("info", a.getUserId() + " has had "+acc.getPoints()+"points added to their account");
        this.accservice.addAccount(a);
    }


  //---------------Takes in the new account and adds it to the database---------------

    @PostMapping(value="/new")
    public Account addAccount(@RequestBody Account acc) {
    	Logging.Log4("info", acc.getUserId() + " has added an account");
        return this.accservice.addAccount(acc);
    }

  //---------------Will return the account, from the database, by the id---------------

    @PostMapping(value="/account")
    public Account getAccountById(@RequestBody Account acc) {
        Account a = this.accservice.findById(acc.getAccId());
        return a;
    }

  //---------------Will return accounts related to the user id and return a list---------------

    @PostMapping(value="/accounts")
    public List<Account> findAccountByUserId(@RequestBody User acc) {
        return this.accservice.findAccountById(acc.getUserId());
    }


}
