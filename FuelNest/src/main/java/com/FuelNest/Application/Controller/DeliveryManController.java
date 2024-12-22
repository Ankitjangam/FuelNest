package com.FuelNest.Application.Controller;

import com.FuelNest.Application.Model.DeliveryMan;
import com.FuelNest.Application.Service.DeliveryServiceRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeliveryManController {

    @Autowired
    private DeliveryServiceRegistration deliveryServiceRegistration;

    @PostMapping("/DeliveryMan/register")
    public ResponseEntity<Map<String, Object>> registerDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Check if password and confirm password match
            if (deliveryMan.getPassword() == null || !deliveryMan.getPassword().equals(deliveryMan.getConfirmPassword())) {
                response.put("message", "Password and Confirm Password do not match!");
                response.put("success", false);
                return ResponseEntity.badRequest().body(response);
            }

            // Call the service method to register the delivery man
            String result = deliveryServiceRegistration.DeliveryManRegister(deliveryMan);

            if ("Email already exists..!".equals(result)) {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(500).body(response);
            }

            response.put("message", result);
            response.put("success", true);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Handle specific exceptions as needed
            response.put("message", "An error occurred: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(500).body(response);
        }
    }
    @PostMapping("/DeliveryMan/login")
    public ResponseEntity<Map<String, Object>> loginDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        Map<String, Object> response = new HashMap<>();

        try {

            // Call the service method to handle login logic
            String result = deliveryServiceRegistration.LoginDeliveryMan(deliveryMan);

            if ("DeliveryMan Login Success....".equals(result)) {
                response.put("message", result);
                response.put("success", true);
                return ResponseEntity.ok(response);  // 200 OK
            } else if ("DeliveryMan Not Found..!".equals(result)) {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
            } else {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // 401 Unauthorized
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "An error occurred during login. Please try again later.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // 500 Internal Server Error
        }
    }
    }

