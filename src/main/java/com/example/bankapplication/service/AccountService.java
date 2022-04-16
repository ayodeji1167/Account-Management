package com.example.bankapplication.service;

import com.example.bankapplication.dto.request.account.*;
import com.example.bankapplication.dto.response.AccountResponse;
import com.example.bankapplication.entity.Account;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    AccountResponse deactivateAccount(DeactivateAccountRequest deactivateAccountRequest);

    AccountResponse creditAccount(CreditAccountRequest creditAccountRequest);

    AccountResponse debitAccount(DebitAccountRequest debitAccountRequest);

    Account updateAccount(UpdateAccountRequest updateAccountRequest);

    Account getAccountByAccountNumber(String accountNumber);
}
