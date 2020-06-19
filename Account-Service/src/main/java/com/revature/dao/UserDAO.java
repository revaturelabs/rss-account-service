package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	
	//login validation dao method
	public boolean existsByEmailAndPassword(String email, String password);
	//getting user information
	public User findUserByEmail(String email);
	public User findUserByUserId(int userId);
}
