package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserDAO;
import com.revature.entity.User;

@Service
public class UserService {

	
	private UserDAO userdao;
	
	@Autowired
	public UserService(UserDAO userdao) {
		this.userdao = userdao;
	}
	
    public List<User> getAllUsers() {
        return this.userdao.findAll();
    }

    public User addUser(User user) {
        return this.userdao.save(user);
    }
	
    public boolean existsByEmailAndPassword(String email, String password) {
    	return this.userdao.existsByEmailAndPassword(email, password);
    }
   
    public User findUserByEmail(String email) {
    	return this.userdao.findUserByEmail(email);
    }
    
}
