package com.naveen.BankApp.service.Imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.BankApp.Dto.AccountDto;
import com.naveen.BankApp.entity.Account;
import com.naveen.BankApp.mapper.AccountMapper;
import com.naveen.BankApp.repository.AccountRepository;
import com.naveen.BankApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccountEntity(accountDto);
		accountRepository.save(account);
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto deposit(Long id, double balance) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		double total = account.getBalance() + balance;
		account.setBalance(total);
		accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto withdraw(Long id, double balance) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		
		if(account.getBalance() < balance) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total = account.getBalance() - balance;
		account.setBalance(total);
		accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((acc) -> AccountMapper.mapToAccountDto(acc)).collect(Collectors.toList());
	}

	@Override
	public String deleteAccount(Long id,Optional<String> name) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
		String accountHolderName = account.getAccountHolderName();
		accountRepository.deleteById(id);
		return accountHolderName;
	}
	
	
}
