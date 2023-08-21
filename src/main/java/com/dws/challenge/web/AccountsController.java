package com.dws.challenge.web;

import java.io.ObjectInput;
import java.math.BigDecimal;

import javax.validation.Valid;

import com.dws.challenge.domain.TransferRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import com.dws.challenge.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/accounts")
@Slf4j
public class AccountsController {

  private final AccountsService accountsService;

  @Autowired
  public AccountsController(AccountsService accountsService) {
    this.accountsService = accountsService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createAccount(@RequestBody @Valid Account account) {
    log.info("Creating account {}", account);

    try {
    this.accountsService.createAccount(account);
    } catch (DuplicateAccountIdException daie) {
      return new ResponseEntity<>(daie.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(path = "/{accountId}")
  public Account getAccount(@PathVariable String accountId) {
    log.info("Retrieving account for id {}", accountId);
    return this.accountsService.getAccount(accountId);
  }
  // Create mapping for transferring money

  @PostMapping(path = "/transfer",consumes = "application/JSON")
  public void transferAmount(@RequestBody TransferRequest transferRequest){
    log.info("transferring the amount");
    String fromAccountID = transferRequest.getFromAccountID();
    String toAccountID = transferRequest.getToAccountID();
    BigDecimal transferAmount = transferRequest.getTransferAmount();
    if(!StringUtils.isEmpty(fromAccountID) && !StringUtils.isEmpty(toAccountID) && transferAmount.compareTo(BigDecimal.ZERO)>0) {
        this.accountsService.transferAmount(fromAccountID,toAccountID,transferAmount);
    }
  }
}
