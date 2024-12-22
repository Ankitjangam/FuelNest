package com.FuelNest.Application.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class DeliveryMan {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "First Name Required")
    private String firstname;

    @NotNull(message = "Last Name Required")
    private String lastname;

    @Email(message = "Email is Required")
    private String email;

    @Column(unique = true)
    private String mobileNumber;

    @NotNull(message = "Password Required")
    private String password;

    @NotNull(message = "Confirm Password Required")
    private String confirmPassword;

    // OTP fields for email verification
    private String emailOtp;
    private LocalDateTime emailOtpExpiration;

    // OTP fields for mobile verification
    private String mobileOtp;
    private LocalDateTime mobileOtpExpiration;

    // Verification status for email and phone
    private boolean isEmailVerified = false;
    private boolean isMobileVerified = false;

}
