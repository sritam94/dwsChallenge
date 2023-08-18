package com.dws.challenge.model;

import java.math.BigDecimal;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;

public interface AccountsModel {

  void transferAmount(String transferFrom, String transferTO, BigDecimal transferAmount);
}
