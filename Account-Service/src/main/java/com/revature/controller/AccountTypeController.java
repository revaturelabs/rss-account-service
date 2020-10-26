package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.AccountType;
import com.revature.service.AccountTypeService;
import com.revature.util.Logging;

@RestController
@RequestMapping("/acctype")
@CrossOrigin
public class AccountTypeController {

	
	private AccountTypeService acctypeservice;
	
	@Autowired
	public AccountTypeController(AccountTypeService acctypeservice) {
		this.acctypeservice = acctypeservice;
	}
	
//---------------As an admin can add account types to the database---------------
    
    @PostMapping(value="/type")
    public AccountType addAccountType(@RequestBody AccountType accType) {
    	Logging.Log4("info", accType.getType() + " has been added");
    	return this.acctypeservice.addAccountType(accType);
    }
	
  //---------------Will pull all of the account types and return a list---------------

    @GetMapping(value="/all")
    public List<AccountType> getAllAccountType() {
        return this.acctypeservice.getAllAccTypes();
    }
    
  //---------------Will update a current account type(for spelling errors, ect)---------------
 
    @PutMapping(value="type/u")
    public void updateAccountType(@RequestBody AccountType accType) {
    	AccountType u = this.acctypeservice.findById(accType.getAccTypeId());
    	u.setType(accType.getType());
        this.acctypeservice.addAccountType(u);
    }
	
}
