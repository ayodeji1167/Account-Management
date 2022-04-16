package com.example.bankapplication.dto.request.account;

import lombok.Data;

@Data
public class DebitAccountRequest {

    private String accountNumber;
    private double amount;
    private String description;
}
