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
	
    public boolean existsByEmail(String email) {
    	return this.userdao.existsByEmail(email);
    }
   
    public User findUserByEmail(String email) {
    	return this.userdao.findUserByEmail(email);
    }
    
    public User findById(int userId) {

        return this.userdao.findUserByUserId(userId);
    }
    public User deleteUser(User u) {
	    this.userdao.delete(u);
	    return u;
    }
    
}
