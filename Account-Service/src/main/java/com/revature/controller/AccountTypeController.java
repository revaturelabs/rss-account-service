package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @RequestMapping(value = "/type", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public AccountType addAccountType(@RequestBody AccountType accType) {
    	Logging.Log4("info", accType.getType() + " has been added");
    	return this.acctypeservice.addAccountType(accType);
    }
	
  //---------------Will pull all of the account types and return a list---------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<AccountType> getAllAccountType() {
        return this.acctypeservice.getAllAccTypes();
    }
    
  //---------------Will update a current account type(for spelling errors, ect)---------------
    @RequestMapping(value = "/type/u", method = RequestMethod.PUT,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public void updateAccountType(@RequestBody AccountType accType) {
    	AccountType u = this.acctypeservice.findById(accType.getAccTypeId());
    	u.setType(accType.getType());
        this.acctypeservice.addAccountType(u);
    }
	
}
