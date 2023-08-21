package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class AccountsService {

  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  public BigDecimal getAccountBalance(String accountId) {
	    return this.accountsRepository.getAccountBalance(accountId);
 }
  
  public void transferAmount(String accountFrom, String accountTo, BigDecimal transferAmount) {
      System.out.println("in here");
	this.accountsRepository.transferAmount(accountFrom, accountTo, transferAmount);
  }
}
