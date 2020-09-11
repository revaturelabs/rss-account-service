package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.User;
import com.revature.service.UserService;
import com.revature.util.Logging;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userservice;
    
    // Bcrypt encryption for user password
    BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();

  //---------------Returns all the users in the database as a List---------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	return userservice.getAllUsers();
    }

  //---------------Adds a new user to the database upon registration---------------
    @RequestMapping(value = "/new", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
    	// Logs a new user
    	Logging.Log4("info", user.getFirstName() + " " + user.getLastName() + " has registered.");
    	
    	// Will encrypt user password for database security
    	user.setPassword(encrypt.encode(user.getPassword()));
    	
    	// sets the email to lowercase to store in the database
    	user.setEmail(user.getEmail().toLowerCase());
    	
        return this.userservice.addUser(user);
    }
	
  //---------------Compares login credentials with whats in the database---------------
    //Will return null if invalid
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User loginUser(@RequestBody User user) {
    	User current = this.userservice.findUserByEmail(user.getEmail().toLowerCase());
    	if(!(this.userservice.existsByEmail(user.getEmail().toLowerCase()) && encrypt.matches(user.getPassword(), current.getPassword()))) {
    		return null;
    	} else {
    		User u = this.userservice.findUserByEmail(user.getEmail().toLowerCase());
    		u.setPassword("*****");
    		Logging.Log4("info", u.getUserId() + " has logged in");
    		return u;
    	}
    }

  //---------------Will pull a user from database by the id or email---------------
    @RequestMapping(value = "/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User findUserById(@RequestBody User user) {
    	User u = this.userservice.findById(user.getUserId());
    	if(u==null) {
    		u = this.userservice.findUserByEmail(user.getEmail().toLowerCase());
    	}
    	//changing password before sending user to front end for security
    	u.setPassword("*****");
    	return u;
    }
    
  //---------------Will Take in new user info and update the user in the database---------------
    @RequestMapping(value= "/info", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updateInformation(@RequestBody User user) {
        User u = this.userservice.findById(user.getUserId());
        u.setEmail(user.getEmail().toLowerCase());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        Logging.Log4("info", user.getUserId() + " has updated their information");
        this.userservice.addUser(u);
    }
    
  //---------------Will take in new user password and encrypt before updating database---------------
    @RequestMapping(value= "/cred", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public void updatePassword(@RequestBody User user) {
    	User u = this.userservice.findById(user.getUserId());
    	u.setPassword(encrypt.encode(user.getPassword()));
        Logging.Log4("info", u.getUserId() + " has updated their password");
        this.userservice.addUser(u);
    }
    
  //---------------Will take an image and update it to the database---------------
    @RequestMapping(value= "/pic", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updateProfilePic(@RequestBody User user) {
        User u = this.userservice.findById(user.getUserId());
        u.setProfilePic(user.getProfilePic());
        Logging.Log4("info", u.getUserId() + " has updated their profile picture");
        this.userservice.addUser(u);
    }
    
  //---------------Updates user to admin---------------
    @RequestMapping(value= "/master", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updateIsAdmin(@RequestBody User user) {
        User u = this.userservice.findById(user.getUserId());
        if(u.isAdmin() == true) {
        	u.setAdmin(false);
        	this.userservice.addUser(u);
        } else {
        	u.setAdmin(true);
        	this.userservice.addUser(u);
        }
        Logging.Log4("info", "Updated admin status to " + u.isAdmin() + " for user with id " + user.getUserId());
    }
   
}