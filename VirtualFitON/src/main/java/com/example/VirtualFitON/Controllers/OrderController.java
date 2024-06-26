package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.OrderDetailsDTO;
import com.example.VirtualFitON.DTO.OrderRequest;
import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/{orderId}-{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("orderStatus") String orderStatus) {

        Order updatedOrder = orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok(updatedOrder);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Integer orderId) {
        OrderDetailsDTO orderDetailsDTO = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetailsDTO);
    }
    @PostMapping("/getOrder")
    public ResponseEntity<?> getOrderDetails( @RequestBody OrderRequest orderRequest) {
        int orderId = orderRequest.getOrderId();
        System.out.println("Order ID received "+orderId);
        return  ResponseEntity.ok(orderService.getOrderDetails(orderId));

    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }



}
