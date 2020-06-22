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

import com.revature.beans.Encryption;
import com.revature.entity.User;
import com.revature.service.UserService;
import com.revature.util.Logging;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userservice;

    //changed "/all" to "/all"
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
    	return userservice.getAllUsers();
    }

    //changed "/adduser" to "/user"
    @RequestMapping(value = "/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
    	
    	// Logs a new user
    	Logging.Log4("info", user.getFirstName() + " " + user.getLastName() + " has registered.");
    	
    	// Will encrypt user password for database security
    	user.setPassword(Encryption.encrypt(user.getPassword()));
    	
        return this.userservice.addUser(user);
    }
	
    @RequestMapping(value = "/login", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User loginUser(@RequestBody User user) {
    	if(this.userservice.existsByEmailAndPassword(user.getEmail(), Encryption.encrypt(user.getPassword())) == false) {
    		return null;
    	} else {
    		User u = this.userservice.findUserByEmail(user.getEmail());
    		u.setPassword("*****");
    		Logging.Log4("info", u.getUserId() + " has logged in");
    		return u;
    	}
    }
    
    //changed "/getuserbyemail" to 
//    @RequestMapping(value = "/getuserbyemail", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(code = HttpStatus.OK)
//    @ResponseBody()
//    public User findUserByEmail(@RequestBody User user) {
//    	User u = this.userservice.findUserByEmail(user.getEmail());
//    	//changing password before sending user to front end for security
//    	u.setPassword("*****");
//    	return u;
//    }

    //Changed "/getuserbyid" to "/user"
    @RequestMapping(value = "/user", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public User findUserById(@RequestBody User user) {
    	User u = this.userservice.findById(user.getUserId());
    	if(u==null) {
    		u = this.userservice.findUserByEmail(user.getEmail());
    	}
    	//changing password before sending user to front end for security
    	u.setPassword("*****");
    	return u;
    }
    
    //changed "/updateinf" to "/update/i"
    @RequestMapping(value= "/update/i", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updateInformation(@RequestBody User user) {
        User u = this.userservice.findById(user.getUserId());
        u.setEmail(user.getEmail());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        Logging.Log4("info", user.getUserId() + " has updated their information");
        this.userservice.addUser(u);
    }
    
    //changed "/updatepassword" to "/update/p"
    @RequestMapping(value= "/update/p", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    public void updatePassword(@RequestBody User user) {
    	User u = this.userservice.findById(user.getUserId());
        u.setPassword(Encryption.encrypt(user.getPassword()));
        Logging.Log4("info", u.getUserId() + " has updated their password");
        this.userservice.addUser(u);
    }
    
    //changed "updateprofilepic" to "/update/pp"
    @RequestMapping(value= "/update/pp", method = RequestMethod.POST,
    		consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public void updateProfilePic(@RequestBody User user) {
        User u = this.userservice.findById(user.getUserId());
        u.setProfilePic(user.getProfilePic());
        Logging.Log4("info", u.getUserId() + " has updated their profile picture");
        this.userservice.addUser(u);
    }
    
    //chagned "/updateisadmin" to "/update/a"
    @RequestMapping(value= "/update/a", method = RequestMethod.POST,
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