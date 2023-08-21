package com.dws.challenge.domain;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String fromAccountID;
    private String toAccountID;
    private BigDecimal transferAmount;

}
