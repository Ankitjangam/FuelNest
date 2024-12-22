package com.FuelNest.Application.Service;

import com.FuelNest.Application.Model.User;
import com.FuelNest.Application.Repository.UserRepository;
import com.FuelNest.Application.WebConfic.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserServiceRegistaration {

    @Autowired
    private UserRepository userRepository;

          @Autowired
    private SecurityConfig securityConfig;

    // Method to register User
    public String registerUser(User user) {
        try {
            // Check if email already exists
            if (userRepository.existsByEmail(user.getEmail())) {
                return "Email already exists!";
            }

            // Password matching check
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                return "Password and confirm password do not match!";
            }

            // Password encoding
            String encodedPassword = securityConfig.passwordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);

                        // Save user to the database
                userRepository.save(user);


            return "User registered successfully!";

            // Return success message
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return "An error occurred during registration. Please try again later.";
        }
    }


    public String UserLogin(String email,String password) {
    try{
        Optional<User>  optional = userRepository.findByEmail(email);

        if(optional.isEmpty()) {
            return"User Not Found Please Register";
        }

        User user=optional.get();



        if(securityConfig.passwordEncoder().matches(password,user.getPassword())){
            return "User Login successful!";
        }
        else {
            return "Invalid credentials or login failed.";
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred during login. Please try again later.";
    }


    }

}
