package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CustomerController(CustomerService customerService, RedisTemplate<String, String> redisTemplate) {
        this.customerService = customerService;
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("/getCustomerId")
    public ResponseEntity<String> getCustomerId(HttpServletRequest request) {


        String authorizationHeader = request.getHeader("Authorization");

        String sessionId = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            sessionId = authorizationHeader.substring(7);
        }

        if (sessionId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing session ID");
        }
        System.out.println("auth head:"+ authorizationHeader);

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String customerId = hashOperations.get( sessionId, "customerId");

        if (customerId != null) {
            return ResponseEntity.ok( customerId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer ID not found for the session ID");
        }
    }



//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody CustomerRegisterDTO requestDto) {
//        try {
//            customerService.registerCustomer(requestDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//        } catch (UsernameAlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error:"+ e.getMessage());
//        } catch (SecurityException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Security error");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }


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

    @GetMapping("/cart/{customerId}")
    public ResponseEntity<?> getCartItems(@PathVariable int customerId) {
        try {
            List<CartItemDTO> products = customerService.getCustomerCartItems(customerId);
            System.out.println("cart items count at controller"+ products.size());
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}
