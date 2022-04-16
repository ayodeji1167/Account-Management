package com.example.bankapplication.serviceimpl;

import com.example.bankapplication.dto.request.account.*;
import com.example.bankapplication.dto.response.AccountResponse;
import com.example.bankapplication.entity.Account;
import com.example.bankapplication.entity.AppUser;
import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.enums.TransactionType;
import com.example.bankapplication.exception.AccountException;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.AppUserRepository;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AppUserRepository appUserRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AppUserRepository appUserRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.appUserRepository = appUserRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        var account = accountRepository.findByAppUser_PhoneNumber(request.getPhoneNumber());
        if (account.isPresent()) {
            throw new AccountException("Phone number has  been already linked with an account");
        }
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setPhoneNumber(request.getPhoneNumber());
        appUserRepository.save(appUser);

        Account account1 = new Account();
        account1.setAccountNumber(String.valueOf(new Random().nextInt(9999) + 1000));
        account1.setAccountType(request.getAccountType());
        account1.setActive(true);
        account1.setBalance(BigDecimal.valueOf(0));
        account1.setAppUser(appUser);
        account1.setCreatedDate(LocalDateTime.now());

        accountRepository.save(account1);

        return convertAccEntityToResponse(account1);
    }

    private AccountResponse convertAccEntityToResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setActive(account.isActive());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setFirstName(account.getAppUser().getFirstName());
        accountResponse.setLastName(account.getAppUser().getLastName());
        accountResponse.setCreatedDate(account.getCreatedDate());

        return accountResponse;
    }

    @Override
    public AccountResponse deactivateAccount(DeactivateAccountRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("No account with given account  number"));

        if (!account.isActive()) {
            throw new AccountException("This wallet is already active");
        }

        account.setActive(false);
        account.setDateDeactivated(LocalDateTime.now());

        accountRepository.save(account);

        return convertAccEntityToResponse(account);
    }

    @Override
    public AccountResponse creditAccount(CreditAccountRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("No account with given account  number"));
        if (!account.isActive()) {
            throw new AccountException("This account is not active");
        }

        var newBalance = BigDecimal.valueOf(request.getAmount()).add(account.getBalance());
        account.setBalance(newBalance);
        accountRepository.save(account);
        saveTransaction(TransactionType.CREDIT, request.getDescription(), request.getAmount(), account);

        return convertAccEntityToResponse(account);
    }

    @Override
    public AccountResponse debitAccount(DebitAccountRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("No account with given account  number"));
        if (!account.isActive()) {
            throw new AccountException("This account is not active");
        }
        var accountBalance = account.getBalance();
        var debitAmount = BigDecimal.valueOf(request.getAmount());

        if (accountBalance.compareTo(debitAmount) < 0) {
            throw new AccountException("Insufficient balance");
        }

        var newBalance = accountBalance.subtract(debitAmount);
        account.setBalance(newBalance);

        accountRepository.save(account);

        saveTransaction(TransactionType.DEBIT, request.getDescription(), request.getAmount(), account);

        return convertAccEntityToResponse(account);
    }

    @Override
    public Account updateAccount(UpdateAccountRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountException("No account with given account  number"));
        if (!account.isActive()) {
            throw new AccountException("This account is not active");
        }

        var appUser =appUserRepository.getAppUserById(account.getAppUser().getId()).get();

        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setPhoneNumber(request.getPhoneNumber());

        appUserRepository.save(appUser);

        account.setAccountType(request.getAccountType());
        account.setAppUser(appUser);

        accountRepository.save(account);

        return account;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new AccountException("No account with account number given"));
    }


    private void saveTransaction(TransactionType type, String description, double amount, Account account) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setCreatedDate(LocalDateTime.now());
        transaction.setSenderId(account.getAppUser().getId());
        transactionRepository.save(transaction);
    }
}
