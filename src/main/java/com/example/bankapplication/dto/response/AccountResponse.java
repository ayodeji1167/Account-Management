package com.example.bankapplication.dto.response;

import com.example.bankapplication.enums.AccountType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponse implements Serializable {
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime dateDeactivated;
    private String firstName;
    private String lastName;
}
