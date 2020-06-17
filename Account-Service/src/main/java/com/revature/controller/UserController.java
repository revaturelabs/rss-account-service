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

import com.revature.entity.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userservice;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userservice.getAllUsers();
    }

    
    @RequestMapping(value = "/adduser", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
        return this.userservice.addUser(user);
    }
	
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User loginUser(@RequestBody User user) {
    	if(this.userservice.existsByEmailAndPassword(user.getEmail(), user.getPassword()) == false) {
    		return null;
    	} else {
    		User u = this.userservice.findUserByEmail(user.getEmail());
    		u.setPassword("*****");
    		return u;
    	}
    }
    
    @RequestMapping(value = "/getuserbyemail", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User findUserByEmail(@RequestBody User user) {
    	User u = this.userservice.findUserByEmail(user.getEmail());
    	//changing password before sending user to front end for security
    	u.setPassword("*****");
    	return u;
    }
    
}
