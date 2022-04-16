package com.example.bankapplication.repository;

import com.example.bankapplication.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, Long> {

    Optional<AppUser> getAppUserById(String id);
}