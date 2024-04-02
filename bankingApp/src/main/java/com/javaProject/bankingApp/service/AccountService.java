package com.javaProject.bankingApp.service;

import java.util.List;

import com.javaProject.bankingApp.dto.AccountDto;

public interface AccountService {
	
	AccountDto createaccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id , double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
