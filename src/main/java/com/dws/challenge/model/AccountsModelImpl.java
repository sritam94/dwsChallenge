package com.dws.challenge.model;

import java.math.BigDecimal;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountsModelImpl implements AccountsModel {

	AccountsService accountsService;
	NotificationService notificationService;

	@Override
	public void transferAmount(String transferFrom, String transferTo, BigDecimal transferAmount) {
		BigDecimal fromAccountBalance = accountsService.getAccountBalance(transferFrom);
		BigDecimal toAccountBalance = accountsService.getAccountBalance(transferTo);
		if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
			log.error("Please enter a positive Amount");
		}
		else if(fromAccountBalance.compareTo(transferAmount)<0)
            log.error("Insufficient Funds");
		else{
			accountsService.getAccount(transferFrom).setBalance(fromAccountBalance.subtract(transferAmount));
			accountsService.getAccount(transferTo).setBalance(toAccountBalance.add(transferAmount));
		}
		String notificationMessageSender = transferAmount.toString()+" has been transferred from your Account to Account No: "+transferTo;
		String notificationMessageReciever = transferAmount.toString()+" has been trasnsferred to your Account from Account No: "+transferFrom;
		notificationService.notifyAboutTransfer(accountsService.getAccount(transferTo),notificationMessageReciever);
		notificationService.notifyAboutTransfer(accountsService.getAccount(transferFrom),notificationMessageSender);
		
	}

}
