package com.FuelNest.Application.Controller;

import com.FuelNest.Application.Model.FuelStation;
import com.FuelNest.Application.Service.FuelStationServiceRegistration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class FuelStationController {

    @Autowired
    private FuelStationServiceRegistration fuelStationService;

    //Registrations

    @PostMapping("/station/register")
    public ResponseEntity<Map<String, Object>> registerAdmin(@Valid @RequestBody FuelStation registrationData) {

        Map<String, Object> response = new HashMap<>();

        String result = fuelStationService.registerStation(registrationData);
               // Check if password and confirmPassword match
        if ("Passwords do not match!".equals(result)) {
            response.put("message", "Password and confirm password do not match.");
            response.put("success", false);
            return ResponseEntity.badRequest().body(response); // Sending mismatch message as JSON
        }

        // Return appropriate response based on the service method result
        if (result.equals("Station registered successfully!")) {
            response.put("message", result);
            response.put("success", true);
            return ResponseEntity.ok(response);  // Success response as JSON
        } else {
            response.put("message", result);  // Error message from service
            response.put("success", false);
            return ResponseEntity.status(500).body(response);  // Error during registration
        }
    }






    // Login Endpoint
    @PostMapping("/station/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestParam String email, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Call service to validate login
            String result = fuelStationService.validateStationLogin(email, password);

            if ("Login successful!".equals(result)) {
                response.put("message", result);
                response.put("success", true);
                return ResponseEntity.ok(response); // 200 OK
            } else if ("User not found!".equals(result)) {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found
            } else {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401 Unauthorized
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "An error occurred during login. Please try again later.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }





    // Endpoint to get the fuel station profile by ID
    @GetMapping("/station/{id}")
    public ResponseEntity<FuelStation> getFuelStation(@PathVariable Long id) {
        Optional<FuelStation> fuelStation = fuelStationService.getFuelStationById(id);
        return fuelStation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Update Fuel Station Profile
    @PutMapping("/station/{id}")
    public ResponseEntity<Map<String, Object>> updateFuelStation(
            @PathVariable Long id,
            @Valid @RequestBody FuelStation fuelStationDetails) {

        Map<String, Object> response = new HashMap<>();


        // Check if Fuel Station exists
        Optional<FuelStation> existingFuelStation = fuelStationService.getFuelStationById(id);
        if (existingFuelStation.isEmpty()) {
            response.put("message", "Fuel station not found");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
        }

        // Proceed with the update if validation is successful
        FuelStation updatedFuelStation = fuelStationService.updateFuelStation(id, fuelStationDetails);
        response.put("message", "Station updated successfully");
        response.put("success", true);
        response.put("data", updatedFuelStation); // Optionally include the updated data in the response
        return ResponseEntity.ok(response);
    }

}
