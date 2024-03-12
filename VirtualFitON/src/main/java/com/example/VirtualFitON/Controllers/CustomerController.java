package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
//import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
//
//        if (customerService.authenticateCustomer(loginRequest)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerRegisterDTO requestDto) {
        try {
            customerService.registerCustomer(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error:"+ e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Security error");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpDTO signUpDTO) {
        try {
            customerService.signUpCustomer(signUpDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error:"+ e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Security error");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            Customer customer=customerService.LoginCustomer(loginDTO);
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error:"+ e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Security error");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
