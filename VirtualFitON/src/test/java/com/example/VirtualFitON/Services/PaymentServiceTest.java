package com.example.VirtualFitON.Services;

import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.*;
import com.example.VirtualFitON.Service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public void testCreateOrder_Success() {
        // Mock customer
        Customer customer = new Customer();
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customer);

        // Mock shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();
        when(shoppingCartRepository.findByCartId(anyInt())).thenReturn(shoppingCart);

        // Mock saved address
        Address savedAddress = new Address();
        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);

        // Mock saved shipment
        Shipment savedShipment = new Shipment();
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(savedShipment);

        // Mock saved order
        Order savedOrder = new Order();
        savedOrder.setOrderId(1);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Set up payment request
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCustomerId(1);
        // Set other payment request properties as needed

        // Call createOrder
        int orderId = paymentService.createOrder(paymentRequest);

        // Verify
        assertEquals(1, orderId);
    }


}
