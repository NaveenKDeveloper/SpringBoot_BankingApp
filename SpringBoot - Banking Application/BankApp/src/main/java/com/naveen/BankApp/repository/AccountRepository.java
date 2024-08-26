package com.naveen.BankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveen.BankApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
