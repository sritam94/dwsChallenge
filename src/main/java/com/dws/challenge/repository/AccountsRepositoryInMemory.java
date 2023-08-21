package com.dws.challenge.repository;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class AccountsRepositoryInMemory implements AccountsRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private NotificationService notificationService;

    @Override
    public void createAccount(Account account) throws DuplicateAccountIdException {
        Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
        if (previousAccount != null) {
            throw new DuplicateAccountIdException(
                    "Account id " + account.getAccountId() + " already exists!");
        }
    }

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
    
    @Override
    public BigDecimal getAccountBalance(String accountId) {
        Account a =  accounts.get(accountId);
        return a.getBalance();
    }

    @Override
    public void clearAccounts() {
        accounts.clear();
    }

    @Override
    public void transferAmount(String transferFrom, String transferTo, BigDecimal transferAmount) {
        BigDecimal fromAccountBalance = getAccountBalance(transferFrom);
        BigDecimal toAccountBalance = getAccountBalance(transferTo);
        if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Please enter a positive Amount");
        }
        else if(fromAccountBalance.compareTo(transferAmount)<0)
            log.error("Insufficient Funds");
        else{
            getAccount(transferFrom).setBalance(fromAccountBalance.subtract(transferAmount));
            getAccount(transferTo).setBalance(toAccountBalance.add(transferAmount));
        }
        log.info("Transfer is done");
        String notificationMessageSender = transferAmount.toString()+" has been transferred from your Account to Account No: "+transferTo;
        String notificationMessageReciever = transferAmount.toString()+" has been trasnsferred to your Account from Account No: "+transferFrom;
        notificationService.notifyAboutTransfer(getAccount(transferTo),notificationMessageReciever);
        notificationService.notifyAboutTransfer(getAccount(transferFrom),notificationMessageSender);

    }

}
