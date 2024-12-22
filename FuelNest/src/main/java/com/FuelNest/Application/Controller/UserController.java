package com.FuelNest.Application.Controller;

import com.FuelNest.Application.Model.User;
import com.FuelNest.Application.Service.UserServiceRegistaration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserServiceRegistaration userService;

    @PostMapping("/user/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        String result = userService.registerUser(user);

        // Password Validation
        if ("Password and confirm password do not match!".equals(result)) {
            response.put("message", "Password and confirm password do not match.");
            response.put("success", false);
            return ResponseEntity.badRequest().body(response);
        }



        // Return Response
        if (result.equalsIgnoreCase("User registered successfully!")) {
            response.put("message", result);
            response.put("success", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", result);
            response.put("success", false);
            return ResponseEntity.status(500).body(response);
        }
    }


@PostMapping("/user/login")
   public  ResponseEntity<Map<String, Object>> LoginUser(@RequestParam String email, @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();
        try {

            String result = userService.UserLogin(email,password);

            if(result.equals("User Login successful!")) {
                response.put("message", result);
                response.put("success", true);
                return ResponseEntity.ok(response);
            }
            else if ("User not found!".equals(result)) {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            else {
                response.put("message", result);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "An error occurred during login. Please try again later.");
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
   }
}
