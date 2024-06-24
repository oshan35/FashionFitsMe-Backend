package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.Address;
import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        String email = paymentRequest.getEmail();
        String phone = paymentRequest.getPhone();
        int customerId=paymentRequest.getCustomerId();
        Address shippingAddress = paymentRequest.getShippingDetails();
        String selectedDeliveryMethod = paymentRequest.getSelectedDeliveryMethod();
        double total= paymentRequest.getTotal();
        double subTotal= paymentRequest.getSubTotal();
        double shipping= paymentRequest.getShipping();
        double taxes= paymentRequest.getTaxes();


        System.out.println("Payment data received:");
        System.out.println("CustomerId: " + customerId);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Shipping Company: " + shippingAddress.getCompany());
        System.out.println("address" + shippingAddress.getAddressName());
        System.out.println("Shipping street" + shippingAddress.getStreet());
        System.out.println("Shipping city" + shippingAddress.getCity());
        System.out.println(" region" + shippingAddress.getRegion());
        System.out.println("postal code" +shippingAddress.getPostalCode());
        System.out.println("total: " + total);
        System.out.println("subTotal: " + subTotal);
        System.out.println("taxes: " + taxes);
        System.out.println("shipping: " + shipping);
        System.out.println("Selected Delivery Method: " + selectedDeliveryMethod);


        int orderId=paymentService.createOrder(paymentRequest);
        return ResponseEntity.ok().body(orderId);
    }
}
