package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
//import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {

        if (customerService.authenticateCustomer(loginRequest)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerRegisterDTO requestDto) {
        try {
            customerService.registerCustomer(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
    }

}
