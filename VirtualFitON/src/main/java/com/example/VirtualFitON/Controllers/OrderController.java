package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.OrderRequest;
import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")

@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/getOrder")
    public ResponseEntity<?> getOrderDetails( @RequestBody OrderRequest orderRequest) {
        int orderId = orderRequest.getOrderId();
        System.out.println("Order ID received "+orderId);
        return  ResponseEntity.ok(orderService.getOrderDetails(orderId));

    }

}
