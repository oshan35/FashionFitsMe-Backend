package com.example.VirtualFitON.Services;

import com.example.VirtualFitON.Controllers.CustomerController;
import com.example.VirtualFitON.DTO.CartItemDTO;
import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import com.example.VirtualFitON.Service.CustomerService;
import com.example.VirtualFitON.Service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ProductShoppingCartRepository productShoppingCartRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartService shoppingCartService;


    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAuthenticateCustomer_ValidCredentials_ReturnTrue() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("tnehara831@gmai.com", "Satn200@");
        Customer customer = new Customer();
        customer.setPassword("encodedPassword");
        when(customerRepository.findByUsername(Mockito.anyString())).thenReturn(customer);
        when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        boolean result = customerService.authenticateCustomer(loginRequestDto);

        assertTrue(result);
    }

    @Test
    public void testAuthenticateCustomer_InvalidCredentials_ReturnFalse() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("tnehara831@gmail.com", "Satn200");
        when(customerRepository.findByUsername(Mockito.anyString())).thenReturn(null);

        boolean result = customerService.authenticateCustomer(loginRequestDto);

        assertFalse(result);
    }

    @Test
    public void getCustomerCartItems_ValidCustomer_ReturnsCartItems() {

        int customerId = 1;
        int cartId = 1;
        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
        CartItemDTO cartItemDTO = new CartItemDTO();
        Product product=new Product();
//
//        List<CartItemDTO> expectedCartItems = new ArrayList<>();
//        expectedCartItems.add(cartItemDTO);
//        expectedCartItems.add(cartItemDTO);


        // Mocking
        when(customerRepository.findCartId(customerId)).thenReturn(cartId);
        when(productShoppingCartRepository.findCartProductsByCartId(cartId)).thenReturn(List.of(productShoppingCart));
        when(shoppingCartService.getCartProductList(List.of(productShoppingCart))).thenReturn(List.of(cartItemDTO));

        // Execution
        List<CartItemDTO> result = customerService.getCustomerCartItems(customerId);
//        System.out.println("result size at test"+ result.size());

        // Assertion
        assertNotNull(result);
        assertFalse(result.isEmpty());
//        assertEquals(4, result.size());


        List<ProductShoppingCart> products= productShoppingCartRepository.findCartProductsByCartId(cartId);
        System.out.println("product size at test"+ products.size());
        assertEquals(4, result.size());

        // Verify
        verify(customerRepository).findCartId(customerId);
        verify(productShoppingCartRepository).findCartProductsByCartId(cartId);
        verify(shoppingCartService).getCartProductList(any());
    }


}

