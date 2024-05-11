package com.example.VirtualFitON.Controllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.AddProductToCartRequest;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.ProductShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductShoppingCartController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductShoppingCartControllerTest {
    @Autowired
    private ProductShoppingCartController productShoppingCartController;

    @MockBean
    private ProductShoppingCartRepository productShoppingCartRepository;

    @MockBean
    private ProductShoppingCartService productShoppingCartService;

    /**
     * Method under test:
     * {@link ProductShoppingCartController#getProductListByCartId(int)}
     */
    @Test
    void testGetProductListByCartId() throws Exception {
        // Arrange
        when(productShoppingCartService.getProductListByCartId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product_shopping_cart/{cartId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartController#addProductToCart(AddProductToCartRequest)}
     */
    @Test
    void testAddProductToCart() throws Exception {
        // Arrange
        doNothing().when(productShoppingCartService).addProductToCart(Mockito.<String>any(), anyInt());

        AddProductToCartRequest addProductToCartRequest = new AddProductToCartRequest();
        addProductToCartRequest.setCustomerId(1);
        addProductToCartRequest.setProductId("42");
        String content = (new ObjectMapper()).writeValueAsString(addProductToCartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product_shopping_cart/addProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product added to cart successfully"));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartController#addProductToCart(AddProductToCartRequest)}
     */
    @Test
    void testAddProductToCart2() throws Exception {
        // Arrange
        doThrow(new InvalidProductDataException("An error occurred")).when(productShoppingCartService)
                .addProductToCart(Mockito.<String>any(), anyInt());

        AddProductToCartRequest addProductToCartRequest = new AddProductToCartRequest();
        addProductToCartRequest.setCustomerId(1);
        addProductToCartRequest.setProductId("42");
        String content = (new ObjectMapper()).writeValueAsString(addProductToCartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product_shopping_cart/addProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid product data: An error occurred"));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartController#addProductToCart(AddProductToCartRequest)}
     */
    @Test
    void testAddProductToCart3() throws Exception {
        // Arrange
        doThrow(new ProductAlreadyExistsException("An error occurred")).when(productShoppingCartService)
                .addProductToCart(Mockito.<String>any(), anyInt());

        AddProductToCartRequest addProductToCartRequest = new AddProductToCartRequest();
        addProductToCartRequest.setCustomerId(1);
        addProductToCartRequest.setProductId("42");
        String content = (new ObjectMapper()).writeValueAsString(addProductToCartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product_shopping_cart/addProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product already exists in the cart: An error occurred"));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartController#getProductColorSizeByCartId(int)}
     */
    @Test
    void testGetProductColorSizeByCartId() throws Exception {
        // Arrange
        when(productShoppingCartService.getProductColorSizeByCartId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product_shopping_cart/{cartId}/product-color-sizes", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartController#getProductColorSizeByCartId(int)}
     */
    @Test
    void testGetProductColorSizeByCartId2() throws Exception {
        // Arrange
        when(productShoppingCartService.getProductColorSizeByCartId(anyInt()))
                .thenThrow(new InvalidProductDataException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product_shopping_cart/{cartId}/product-color-sizes", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ProductShoppingCartController#getTotals(int)}
     */
    @Test
    void testGetTotals() throws Exception {
        // Arrange
        when(productShoppingCartService.getDiscountAmount(anyInt())).thenReturn(new BigDecimal("2.3"));
        when(productShoppingCartService.getTotalAmount(anyInt())).thenReturn(new BigDecimal("2.3"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product_shopping_cart/totals/{cartId}",
                1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productShoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"totalAmount\":2.3,\"discountAmount\":2.3,\"estimatedTotal\":0.0}"));
    }

}
