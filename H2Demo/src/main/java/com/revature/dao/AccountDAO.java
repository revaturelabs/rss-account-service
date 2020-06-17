package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {

}
