package com.naveen.BankApp.service;

import java.util.List;
import java.util.Optional;

import com.naveen.BankApp.Dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double balance);
	
	AccountDto withdraw(Long id,double balance);
	
	List<AccountDto> getAllAccounts();
	
	String deleteAccount(Long id,Optional<String> name);
}
