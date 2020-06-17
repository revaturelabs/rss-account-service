package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserDAO;
import com.revature.entity.User;

@Service
public class UserService {

	@Autowired
	UserDAO userdao;
	
    public List<User> getAllUsers() {
        return this.userdao.findAll();
    }

    public User addUser(User user) {
        return this.userdao.save(user);
    }
	
}
