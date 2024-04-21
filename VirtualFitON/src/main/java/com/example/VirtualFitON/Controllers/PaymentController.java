package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PaymentController {
    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        String email = paymentRequest.getEmail();
        String phone = paymentRequest.getPhone();
        int customerId=paymentRequest.getCustomerId();
        Address shippingDetails = paymentRequest.getShippingDetails();
        String selectedDeliveryMethod = paymentRequest.getSelectedDeliveryMethod();


        // Simulate processing the payment
        System.out.println("Payment data received:");
        System.out.println("CustomerId: " + customerId);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Shipping Details: " + shippingDetails);
        System.out.println("Selected Delivery Method: " + selectedDeliveryMethod);

        // Return a response indicating success
        return ResponseEntity.ok().body("Payment details received successfully.");
    }
}
