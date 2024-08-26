package com.naveen.BankApp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naveen.BankApp.Dto.AccountDto;
import com.naveen.BankApp.service.AccountService;

@Controller
@RequestMapping("api/accounts")
public class AccountController {
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return  ResponseEntity.ok(accountDto); 
	}
	
	@PutMapping("/account/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
		AccountDto accountDto = accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/account/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
		AccountDto accountDto = accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts= accountService.getAllAccounts();
		return  ResponseEntity.ok(accounts); 
	}
	
	@DeleteMapping("/account/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		String name = accountService.deleteAccount(id,Optional.empty());
		return ResponseEntity.ok(name + " Account is deleted");
	}
}
