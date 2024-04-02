package com.javaProject.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaProject.bankingApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	

}
