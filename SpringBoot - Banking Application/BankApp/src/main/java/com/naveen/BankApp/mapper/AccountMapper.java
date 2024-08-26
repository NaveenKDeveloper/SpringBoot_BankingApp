package com.naveen.BankApp.mapper;

import com.naveen.BankApp.Dto.AccountDto;
import com.naveen.BankApp.entity.Account;

public class AccountMapper {
	
	public static Account mapToAccountEntity(AccountDto accountDto) {
		Account account = new Account(accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance());
		
		return account;
	}
	
	public static AccountDto mapToAccountDto(Account account) {
		
		AccountDto accountDto = new AccountDto(account.getId(),
				account.getAccountHolderName(),
				account.getBalance());
		return accountDto;
	}
	
	
}
