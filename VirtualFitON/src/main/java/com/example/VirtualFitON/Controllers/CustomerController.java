package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
//import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;



    @GetMapping("/getCustomerId")
    public ResponseEntity<String> getCustomerId(HttpServletRequest request) {


        String authorizationHeader = request.getHeader("Authorization");

        String sessionId = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            sessionId = authorizationHeader.substring(7);
        }

        // If session ID is not found, return an error response
        if (sessionId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing session ID");
        }
        System.out.println("auth head:"+ authorizationHeader);

        // Retrieve customer ID from Redis using the session ID
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String customerId = hashOperations.get( sessionId, "customerId");

        // Check if the customer ID was found
        if (customerId != null) {
            // Return the customer ID as a successful response
            return ResponseEntity.ok( customerId);
        } else {
            // Return a not found status if the customer ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not found for the session ID");
        }
    }



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
    public ResponseEntity<?> loginCustomer(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            Customer customer = customerService.LoginCustomer(loginDTO);
            if (customer != null) {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);
                    session.setMaxInactiveInterval(30 * 60);
                } else {
                    request.changeSessionId();
                }



                HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
                hashOperations.put(session.getId(), "customerId", String.valueOf(customer.getCustomerId()));

                Map<String, String> response = new HashMap<>();
                response.put("sessionId", session.getId());

                return ResponseEntity.ok(response);            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
        }
    }



}
