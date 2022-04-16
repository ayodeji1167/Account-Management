package com.example.bankapplication.dto.request.appuser;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AppUserRequest implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
}
