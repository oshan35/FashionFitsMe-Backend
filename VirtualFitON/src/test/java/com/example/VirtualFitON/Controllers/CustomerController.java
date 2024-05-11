package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.UsernameAlreadyExistsException;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerController {

    @Mock
    private CustomerService customerService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        CustomerRegisterDTO requestDto = new CustomerRegisterDTO();
        when(customerService.registerCustomer(requestDto)).thenReturn(true);

        ResponseEntity<String> response = customerController.registerUser(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    public void testRegisterUser_UsernameAlreadyExists() {
        CustomerRegisterDTO requestDto = new CustomerRegisterDTO();
        when(customerService.registerCustomer(requestDto)).thenThrow(UsernameAlreadyExistsException.class);

        ResponseEntity<String> response = customerController.registerUser(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @Test
    public void testSignUpUser_Success() {
        SignUpDTO signUpDTO = new SignUpDTO();
        when(customerService.signUpCustomer(signUpDTO)).thenReturn(true);

        ResponseEntity<String> response = customerController.signUpUser(signUpDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    public void testSignUpUser_UsernameAlreadyExists() {
        SignUpDTO signUpDTO = new SignUpDTO();
        when(customerService.signUpCustomer(signUpDTO)).thenThrow(UsernameAlreadyExistsException.class);

        ResponseEntity<String> response = customerController.signUpUser(signUpDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @Test
    public void testLoginCustomer_Success() {
        LoginDTO loginDTO = new LoginDTO();
        when(customerService.LoginCustomer(loginDTO)).thenReturn(new Customer());

        ResponseEntity<?> response = customerController.loginCustomer(loginDTO, httpServletRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCartItems_Success() {
        int customerId = 1;
        List<CartItemDTO> cartItems = new ArrayList<>();
        when(customerService.getCustomerCartItems(customerId)).thenReturn(cartItems);

        ResponseEntity<?> response = customerController.getCartItems(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItems, response.getBody());
    }

    // Add more test cases as needed
}
