package com.FuelNest.Application.Service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
@Autowired
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Store OTPs with timestamp for verification
    private final Map<String, OtpDetails> emailOtpStore = new HashMap<>();

    public OtpService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    // Method to generate OTP
    private String generateOtp() {
        Random rand = new Random();
        return String.format("%06d", rand.nextInt(1000000));  // OTP is a 6-digit number
    }

    // ================================
    // Email OTP Service
    // ================================
    public void sendEmailOtp(String email) throws MessagingException {
        String otp = generateOtp();  // Generate OTP
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(email);
        helper.setSubject("Your OTP for Email Verification");
        helper.setText("Dear user,\n" +
                "\n" +
                "Thank you for registering with us. To complete your email verification, please enter the following One-Time Password (OTP) in the required field:\n" +
                "\n" +
                "OTP: " + otp +"\n" +
                "\n" +
                "This OTP is valid for the next  5 minutes. Please do not share this code with anyone.\n" +
                "\n" +
                "If you did not request this, please ignore this email." );

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
           System.out.println("Error in sending Email Otp");
        }

        // Store OTP with timestamp for email
        emailOtpStore.put(email, new OtpDetails(otp, System.currentTimeMillis()));
    }


    // ================================
    // Email OTP Verification Method
    // ================================
    public boolean verifyEmailOtp(String email, String otp) {
        OtpDetails otpDetails = emailOtpStore.get(email);

        if (otpDetails == null) {
            return false;  // OTP not found for email
        }

        // Check if OTP is expired
        // OTP expiration time (e.g., 5 minutes)
        long OTP_EXPIRATION_TIME = 5 * 60 * 1000;
        if (System.currentTimeMillis() - otpDetails.getTimestamp() > OTP_EXPIRATION_TIME) {
            emailOtpStore.remove(email);  // Remove expired OTP
            return false;
        }

        // Check if OTP matches
        return otpDetails.getOtp().equals(otp);
    }


    // OTP Details class to store OTP and its timestamp
    @Getter
    private static class OtpDetails {
        private final String otp;
        private final long timestamp;

        public OtpDetails(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

    }
}
