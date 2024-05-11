package com.example.VirtualFitON.Services;

import com.example.VirtualFitON.DTO.OrderDTO;
import com.example.VirtualFitON.DTO.OrderProductDTO;
import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.OrderRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.OrderService;
import com.example.VirtualFitON.Service.ProductShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductShoppingCartService productShoppingCartService;

    @Mock
    private ProductImageRepository productImageRepository;

    @Mock
    private ProductShoppingCartRepository productShoppingCartRepository;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetOrderDetails() {
        // Mocking data
        int orderId = 1;
        Order order = new Order();
        ShoppingCart shoppingCart = new ShoppingCart(1,BigDecimal.valueOf(100.00),false,BigDecimal.valueOf(10.00));


        List<ProductShoppingCart> cartProductList = new ArrayList<>();

        List<Product> cartProductListFromService = new ArrayList<>();


        List<OrderProductDTO> orderProducts = new ArrayList<>();
        // Mocking product image
        byte[] productImage = new byte[0];

        // Mocking repository method calls
        Mockito.when(orderRepository.findByOrderId(orderId)).thenReturn(order);
        Mockito.when(productShoppingCartRepository.findCartProductsByCartId(Mockito.anyInt())).thenReturn(cartProductList);
        Mockito.when(productShoppingCartService.getCartProductListByCartId(Mockito.anyInt())).thenReturn(cartProductListFromService);
        Mockito.when(productImageRepository.findByProductIdAndColor(Mockito.anyString(), Mockito.anyString())).thenReturn(productImage);

        // Calling the method under test
        OrderDTO result = orderService.getOrderDetails(orderId);

        // Verifying the result
        assertEquals(4, result.getProducts().size()); // You may need to implement equals method in OrderDTO and OrderProductDTO for proper comparison
    }

}
