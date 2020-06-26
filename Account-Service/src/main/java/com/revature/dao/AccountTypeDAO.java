package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.AccountType;
import com.revature.entity.User;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountType, Integer> {
	public AccountType findAccTypeByAccTypeId(int accTypeId);
}
