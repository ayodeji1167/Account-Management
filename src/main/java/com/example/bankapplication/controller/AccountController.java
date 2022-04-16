package com.example.bankapplication.controller;

import com.example.bankapplication.dto.request.account.*;
import com.example.bankapplication.dto.response.AccountResponse;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public AccountResponse saveAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return service.createAccount(createAccountRequest);
    }

    @GetMapping("/credit")
    public AccountResponse creditAccount(@RequestBody CreditAccountRequest request) {
        return service.creditAccount(request);
    }

    @GetMapping("/debit")
    public AccountResponse debitAccount(@RequestBody DebitAccountRequest request) {
        return service.debitAccount(request);
    }

    @GetMapping("/deactivate")
    public AccountResponse deactivateAccount(@RequestBody DeactivateAccountRequest deactivateAccountRequest) {
        return service.deactivateAccount(deactivateAccountRequest);
    }
    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return service.getAccountByAccountNumber(accountNumber);
    }

    @PutMapping
    public Account updateAccount(@RequestBody UpdateAccountRequest request) {
        return service.updateAccount(request);
    }
}
