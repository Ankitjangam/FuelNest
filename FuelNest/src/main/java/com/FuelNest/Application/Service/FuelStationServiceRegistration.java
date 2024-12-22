package com.FuelNest.Application.Service;

import com.FuelNest.Application.Model.FuelStation;
import com.FuelNest.Application.Repository.FuelStationRepository;
import com.FuelNest.Application.WebConfic.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuelStationServiceRegistration {

    @Autowired
    private FuelStationRepository fuelStationRepository;
    @Autowired
    private SecurityConfig securityConfig;


    public String registerStation(FuelStation fuelStation) {
        try {
            // Check if email already exists
            if (fuelStationRepository.existsByEmail(fuelStation.getEmail())) {
                return "Email already exists!";
            }

            // Check if passwords match
            if (!fuelStation.getPassword().equals(fuelStation.getConfirmPassword())) {
                return "Passwords do not match!";
            }

            // Hash the password before saving
            String encodedPassword = securityConfig.passwordEncoder().encode(fuelStation.getPassword());

            String phoneNumberWithCode = fuelStation.getCountryCode() + fuelStation.getPhoneNumber();
            fuelStation.setPhoneNumber(phoneNumberWithCode);

            fuelStation.setPassword(encodedPassword);

            fuelStationRepository.save(fuelStation);


            return "Station registered successfully!";
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return "An error occurred during registration. Please try again later.";
        }
    }

    /// /// Login
    public String validateStationLogin(String email, String password) {
        try {
            Optional<FuelStation> optionalStation = fuelStationRepository.findByEmail(email);

            if (optionalStation.isEmpty()) {
                return "Station  not found!";
            }

            FuelStation admin = optionalStation.get();

            // Check if the password matches the hashed password stored in the database
            if (securityConfig.passwordEncoder().matches(password, admin.getPassword())) {
                    return "Login successful!";
            } else {
                return "Invalid credentials or login failed.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during login. Please try again later.";
        }
    }




    // Method to get fuel station profile by ID
    public Optional<FuelStation> getFuelStationById(Long id) {
        return fuelStationRepository.findById(id);
    }

    // Method to update fuel station profile
    public FuelStation updateFuelStation(Long id, FuelStation fuelStationDetails) {
        FuelStation existingFuelStation = fuelStationRepository.findById(id).orElseThrow(() -> new RuntimeException("Fuel Station not found"));

        existingFuelStation.setFirstname(fuelStationDetails.getFirstname());
        existingFuelStation.setLastname(fuelStationDetails.getLastname());
        existingFuelStation.setPhoneNumber(fuelStationDetails.getPhoneNumber());
        existingFuelStation.setCountryCode(fuelStationDetails.getCountryCode());
        existingFuelStation.setGovernmentId(fuelStationDetails.getGovernmentId());
        existingFuelStation.setId_no(fuelStationDetails.getId_no());
        existingFuelStation.setAddress(fuelStationDetails.getAddress());
        existingFuelStation.setNationality(fuelStationDetails.getNationality());
        existingFuelStation.setState(fuelStationDetails.getState());
        existingFuelStation.setDistrict(fuelStationDetails.getDistrict());
        existingFuelStation.setCity(fuelStationDetails.getCity());
        existingFuelStation.setPin_code(fuelStationDetails.getPin_code());
        existingFuelStation.setAddress_line1(fuelStationDetails.getAddress_line1());

        return fuelStationRepository.save(existingFuelStation);
    }
}
