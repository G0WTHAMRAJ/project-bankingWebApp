package com.javaProject.bankingApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaProject.bankingApp.dto.AccountDto;
import com.javaProject.bankingApp.entity.Account;
import com.javaProject.bankingApp.mapper.AccountMapper;
import com.javaProject.bankingApp.repository.AccountRepository;
import com.javaProject.bankingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createaccount(AccountDto accountDto) {
	
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account  account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Not Exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account  account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Not Exist"));
		
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account  account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Not Exist"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
			double total= account.getBalance()-amount;
			account.setBalance(total);
			Account savedAccount= accountRepository.save(account);
			
			return AccountMapper.mapToAccountDto(savedAccount);
			
		
		
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		 return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				 	.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Account  account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Not Exist"));
		
		accountRepository.deleteById(id);
		
		
	}

	
}
