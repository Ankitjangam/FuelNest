package com.FuelNest.Application.Service;

import com.FuelNest.Application.Model.DeliveryMan;
import com.FuelNest.Application.Repository.DeliveryManRepository;
import com.FuelNest.Application.WebConfic.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryServiceRegistration {
    @Autowired
    private DeliveryManRepository deliveryManRepository;
    @Autowired
    private SecurityConfig securityConfig;

    public String DeliveryManRegister(DeliveryMan deliveryMan) {
        try {
            if (deliveryManRepository.existsByEmail(deliveryMan.getEmail())) {
                return "Email already exists..!";
            }

            if (!deliveryMan.getPassword().equals(deliveryMan.getConfirmPassword())) {
                return "Password do not Match!...";
            }

            String encodedPassword=securityConfig.passwordEncoder().encode(deliveryMan.getPassword());
            deliveryMan.setPassword(encodedPassword);

              deliveryManRepository.save(deliveryMan);
             return "DevilryMan registered successfully!";


        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return "An error occurred during registration. Please try again later.";
        }

    }





    //// login logic

    public String LoginDeliveryMan(DeliveryMan deliveryMan) {
        try {
            // Fetch the DeliveryMan from the database by email
            Optional<DeliveryMan> optional = deliveryManRepository.findByEmail(deliveryMan.getEmail());

            // Check if the DeliveryMan exists in the database
            if (optional.isEmpty()) {
                return "DeliveryMan Not Found..!";
            }

            // Retrieve the DeliveryMan object
            DeliveryMan man = optional.get();

            // Compare the passwords
            if (securityConfig.passwordEncoder().matches(deliveryMan.getPassword(), man.getPassword())) {
                return "DeliveryMan Login Success....";
            } else {
                return "Invalid Password! Please enter the correct password.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during login. Please try again later.";
        }
    }



}
