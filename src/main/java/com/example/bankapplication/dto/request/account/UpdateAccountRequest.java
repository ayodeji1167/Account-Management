package com.example.bankapplication.dto.request.account;

import com.example.bankapplication.enums.AccountType;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    private String accountNumber;
    private AccountType accountType;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
