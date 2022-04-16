package com.example.bankapplication.dto.request.account;

import com.example.bankapplication.enums.AccountType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAccountRequest {

    private AccountType accountType;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
