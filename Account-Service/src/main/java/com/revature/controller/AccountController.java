package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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

	
    @RequestMapping(value= "/updatepoints", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updatePoints(@RequestBody Account acc) {
        Account a = this.accservice.findById(acc.getAccId());
        a.setPoints(acc.getPoints());
        Logging.Log4("info", a.getUserId() + " has updated their points");
        this.accservice.addAccount(a);
    }
	
    @RequestMapping(value = "/addaccount", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public Account addAccount(@RequestBody Account acc) {
    	Logging.Log4("info", acc.getUserId() + " has added an account");
        return this.accservice.addAccount(acc);
    }
 
    @RequestMapping(value = "/getaccountbyaccid", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public Account getAccountById(@RequestBody Account acc) {
        return this.accservice.findById(acc.getAccId());
    }
    
    @RequestMapping(value = "/getaccountbyuserid", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<Account> findAccountByUserId(@RequestBody User acc) {
        return this.accservice.findAccountById(acc.getUserId());
    }
}
