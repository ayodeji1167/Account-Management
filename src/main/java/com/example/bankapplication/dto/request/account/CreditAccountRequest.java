package com.example.bankapplication.dto.request.account;

import lombok.Data;

@Data
public class CreditAccountRequest {

    private String accountNumber;
    private double amount;
    private String description;
}
