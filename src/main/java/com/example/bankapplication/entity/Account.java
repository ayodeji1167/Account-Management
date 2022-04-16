package com.example.bankapplication.entity;

import com.example.bankapplication.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account")
public class Account {

    @Id
    private String id;

    @NotNull
    private String accountNumber;

    @NotNull
    private AccountType accountType;

    @NotNull
    private AppUser appUser;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private boolean isActive;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime dateDeactivated;

}
