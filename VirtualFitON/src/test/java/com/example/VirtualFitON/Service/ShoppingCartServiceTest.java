package com.example.VirtualFitON.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.CartItemDTO;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.ProductImageRepository;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ShoppingCartService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ShoppingCartServiceTest {
    @MockBean
    private ProductImageRepository productImageRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Method under test: {@link ShoppingCartService#getCartProductList(List)}
     */
    @Test
    void testGetCartProductList() {
        // Arrange, Act and Assert
        assertTrue(shoppingCartService.getCartProductList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link ShoppingCartService#getCartProductList(List)}
     */
    @Test
    void testGetCartProductList2() throws UnsupportedEncodingException {
        // Arrange
        when(productImageRepository.findByProductIdAndColor(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn("AXAXAXAX".getBytes("UTF-8"));

        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        ProductShoppingCartId id = new ProductShoppingCartId();
        id.setCartId(1);
        id.setProductId("42");

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
        productShoppingCart.setCart(cart);
        productShoppingCart.setId(id);
        productShoppingCart.setOrderQuantity(1);
        productShoppingCart.setProduct(product);
        productShoppingCart.setProductColor("Product Color");
        productShoppingCart.setProductSize("Product Size");

        ArrayList<ProductShoppingCart> cartItems = new ArrayList<>();
        cartItems.add(productShoppingCart);

        // Act
        List<CartItemDTO> actualCartProductList = shoppingCartService.getCartProductList(cartItems);

        // Assert
        verify(productImageRepository).findByProductIdAndColor(eq("42"), eq("Product Color"));
        assertEquals(1, actualCartProductList.size());
    }

    /**
     * Method under test: {@link ShoppingCartService#getCartProductList(List)}
     */
    @Test
    void testGetCartProductList3() throws UnsupportedEncodingException {
        // Arrange
        when(productImageRepository.findByProductIdAndColor(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn("AXAXAXAX".getBytes("UTF-8"));

        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        ProductShoppingCartId id = new ProductShoppingCartId();
        id.setCartId(1);
        id.setProductId("42");

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
        productShoppingCart.setCart(cart);
        productShoppingCart.setId(id);
        productShoppingCart.setOrderQuantity(1);
        productShoppingCart.setProduct(product);
        productShoppingCart.setProductColor("Product Color");
        productShoppingCart.setProductSize("Product Size");

        ShoppingCart cart2 = new ShoppingCart();
        cart2.setCartId(2);
        cart2.setDiscountAmount(new BigDecimal("2.3"));
        cart2.setPurchaseStatus(false);
        cart2.setTotalAmount(new BigDecimal("2.3"));

        ProductShoppingCartId id2 = new ProductShoppingCartId();
        id2.setCartId(2);
        id2.setProductId("Product Id");

        Brand brand2 = new Brand();
        brand2.setBrandId("Brand Id");
        brand2.setBrandName("42");

        Product product2 = new Product();
        product2.setBrand(brand2);
        product2.setGender("42");
        product2.setPrice(new BigDecimal("2.3"));
        product2.setProductCategory("42");
        product2.setProductId("Product Id");
        product2.setProductName("42");

        ProductShoppingCart productShoppingCart2 = new ProductShoppingCart();
        productShoppingCart2.setCart(cart2);
        productShoppingCart2.setId(id2);
        productShoppingCart2.setOrderQuantity(0);
        productShoppingCart2.setProduct(product2);
        productShoppingCart2.setProductColor("42");
        productShoppingCart2.setProductSize("42");

        ArrayList<ProductShoppingCart> cartItems = new ArrayList<>();
        cartItems.add(productShoppingCart2);
        cartItems.add(productShoppingCart);

        // Act
        List<CartItemDTO> actualCartProductList = shoppingCartService.getCartProductList(cartItems);

        // Assert
        verify(productImageRepository, atLeast(1)).findByProductIdAndColor(Mockito.<String>any(), Mockito.<String>any());
        assertEquals(2, actualCartProductList.size());
    }
}
