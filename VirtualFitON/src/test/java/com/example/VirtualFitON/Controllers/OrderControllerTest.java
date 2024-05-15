package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.OrderRequest;
import com.example.VirtualFitON.DTO.OrderDTO;
import com.example.VirtualFitON.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Test
    public void testGetOrderDetails_Success() {
        OrderService orderService = mock(OrderService.class);

        when(orderService.getOrderDetails(1)).thenReturn(new OrderDTO());

        OrderController orderController = new OrderController(orderService);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(1);

        ResponseEntity<?> responseEntity = orderController.getOrderDetails(orderRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(true, responseEntity.getBody() != null);
    }

    @Test
    public void testGetOrderDetails_OrderNotFound() {
        OrderService orderService = mock(OrderService.class);

        when(orderService.getOrderDetails(1)).thenReturn(null);

        OrderController orderController = new OrderController(orderService);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(1);

        ResponseEntity<?> responseEntity = orderController.getOrderDetails(orderRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
