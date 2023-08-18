package com.dws.challenge.model;

import java.math.BigDecimal;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.service.AccountsService;

public class AccountsModelImpl implements AccountsModel {

	AccountsService accountsService;

	@Override
	public void transferAmount(String transferFrom, String transferTO, BigDecimal transferAmount) {
		if (transferAmount.compareTo(BigDecimal.ZERO)==1) {
			System.err.println("Please enter a positive Amount");

		} 
		
	}

}
