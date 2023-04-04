package com.nttdata.MSPayment.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Credit {

    private String id;
    private String idCustomer;
    private String cardNumber;
    private String creditType;
    private String accountNumber;
    private Double balance;
    private Double creditLine;
    private Double debt;
}