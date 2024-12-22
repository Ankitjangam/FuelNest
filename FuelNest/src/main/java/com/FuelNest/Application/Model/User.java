package com.FuelNest.Application.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "First name is required")
    private String firstname;

    @NotNull(message = "Last name is required")
    private String lastname;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Confirm password is required")
    private String confirmPassword;

    @Email(message = "Email should be valid")
    private String email;

    @Column(unique = true)
    private String phoneNumber;


    private String address;
    private String nationality;
    private String state;
    private String district;
    private String city;
    private String pin_code;
    private String address_line1;

    @NotNull(message = "Enter the Email Otp")
    private String emailOtp;

    private long emailOtpTimestamp;

}
