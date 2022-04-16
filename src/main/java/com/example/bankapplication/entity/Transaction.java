package com.example.bankapplication.entity;

import com.example.bankapplication.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    private String id;

    private double amount;

    private String description;

    private TransactionType transactionType;

    private LocalDateTime createdDate;

    private String senderId;

    private Long receiverId;

}
