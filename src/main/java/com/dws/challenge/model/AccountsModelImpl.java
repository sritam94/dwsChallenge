package com.dws.challenge.model;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.service.AccountsService;

public class AccountsModelImpl extends AccountsModel {
	
	AccountsService accountsService = 

  public void transferAmount(String transferFrom, String transferTO, BigDecimal transferAmount) {
	  if(transferAmount<0) {
		  System.out.println("Please enter a positive Amount");
		  
	  }
	  else {
		  if(AccountRepository.getAccount(transformFrom).getBalance()>transferAmount) {
			  AccountRepository.getAccount(transformFrom).getBalance()
		  }
	  }
	  
  }
}
