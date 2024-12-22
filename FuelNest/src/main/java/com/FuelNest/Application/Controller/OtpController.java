package com.FuelNest.Application.Controller;

import com.FuelNest.Application.Service.OtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class OtpController {
    @Autowired
    private  OtpService otpService;

 @CrossOrigin(origins = "http://localhost:63342")
    // Endpoint to send OTP to email
    @PostMapping("/sendEmailOtp")
    public ResponseEntity<Map<String, Object>> sendEmailOtp(@RequestBody Map<String, String> request) throws MessagingException {
        String email = request.get("email");

        otpService.sendEmailOtp(email);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "OTP sent to email: " + email
        ));
    }

    @CrossOrigin(origins = "http://localhost:63342")
    // Endpoint to verify email OTP
    @PostMapping("/verifyEmailOtp")
    public ResponseEntity<Map<String, Object>> verifyEmailOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        boolean isVerified = otpService.verifyEmailOtp(email, otp);

        if (isVerified) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Email OTP verified successfully"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Invalid or expired OTP"
            ));
        }
    }
}
