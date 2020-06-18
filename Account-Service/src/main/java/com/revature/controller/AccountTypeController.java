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
	
    @RequestMapping(value = "/addaccounttype", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public AccountType addAccountType(@RequestBody AccountType accType) {
    	Logging.Log4("info", accType.getType() + " has been added");
    	return this.acctypeservice.addAccountType(accType);
    }
	
    @RequestMapping(value = "/getallaccounttype", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public List<AccountType> getAllAccountType() {
        return this.acctypeservice.getAllAccTypes();
    }
	
}
