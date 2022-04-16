package com.example.bankapplication.repository;

import com.example.bankapplication.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {


    Optional<Account> findByAppUser_PhoneNumber(String phoneNumber);

    Optional<Account> findByAccountNumber(String accountNumber);
}