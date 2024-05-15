package com.example.VirtualFitON.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.VirtualFitON.Controllers.CustomerController;
import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerId_Success() {
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer sessionId");
        when(httpServletRequest.getSession(false)).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn("sessionId");

        ResponseEntity<String> response = customerController.getCustomerId(httpServletRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCustomerId_MissingSessionId() {
        when(httpServletRequest.getHeader("Authorization")).thenReturn(null);

        ResponseEntity<String> response = customerController.getCustomerId(httpServletRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Missing session ID", response.getBody());
    }

    @Test
    public void testRegisterUser_Success() {
        CustomerRegisterDTO requestDto = new CustomerRegisterDTO();

        ResponseEntity<String> response = customerController.registerUser(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }


}
