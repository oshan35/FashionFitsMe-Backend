package com.example.VirtualFitON.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Exceptions.DatabaseAccessException;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductShoppingCartService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductShoppingCartServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ProductColorSizeRepository productColorSizeRepository;

    @MockBean
    private ProductImageRepository productImageRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductShoppingCartRepository productShoppingCartRepository;

    @Autowired
    private ProductShoppingCartService productShoppingCartService;

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * Method under test:
     * {@link ProductShoppingCartService#getProductListByCartId(int)}
     */
    @Test
    void testGetProductListByCartId() {
        // Arrange
        when(productShoppingCartRepository.findProductsByCartId(anyInt())).thenReturn(new ArrayList<>());

        // Act
        List<ProductDTO> actualProductListByCartId = productShoppingCartService.getProductListByCartId(1);

        // Assert
        verify(productShoppingCartRepository).findProductsByCartId(eq(1));
        assertTrue(actualProductListByCartId.isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#getProductListByCartId(int)}
     */
    @Test
    void testGetProductListByCartId2() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productShoppingCartRepository.findProductsByCartId(anyInt())).thenReturn(productList);

        // Act
        List<ProductDTO> actualProductListByCartId = productShoppingCartService.getProductListByCartId(1);

        // Assert
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productShoppingCartRepository).findProductsByCartId(eq(1));
        assertEquals(1, actualProductListByCartId.size());
        ProductDTO getResult = actualProductListByCartId.get(0);
        assertEquals("Gender", getResult.getProduct().getGender());
        assertTrue(getResult.getProductImages().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#getProductListByCartId(int)}
     */
    @Test
    void testGetProductListByCartId3() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new DuplicateKeyException("Msg"));

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

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productShoppingCartRepository.findProductsByCartId(anyInt())).thenReturn(productList);

        // Act and Assert
        assertThrows(DuplicateKeyException.class, () -> productShoppingCartService.getProductListByCartId(1));
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productShoppingCartRepository).findProductsByCartId(eq(1));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#addProductToCart(String, int)}
     */
    @Test
    void testAddProductToCart()
            throws DatabaseAccessException, InvalidProductDataException, ProductAlreadyExistsException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        Customer customer = new Customer();
        customer.setCart(cart);
        customer.setCountry("GB");
        customer.setCustomerId(1);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customer);

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
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);

        ShoppingCart cart2 = new ShoppingCart();
        cart2.setCartId(1);
        cart2.setDiscountAmount(new BigDecimal("2.3"));
        cart2.setPurchaseStatus(true);
        cart2.setTotalAmount(new BigDecimal("2.3"));

        ProductShoppingCartId id = new ProductShoppingCartId();
        id.setCartId(1);
        id.setProductId("42");

        Brand brand2 = new Brand();
        brand2.setBrandId("42");
        brand2.setBrandName("Brand Name");

        Product product2 = new Product();
        product2.setBrand(brand2);
        product2.setGender("Gender");
        product2.setPrice(new BigDecimal("2.3"));
        product2.setProductCategory("Product Category");
        product2.setProductId("42");
        product2.setProductName("Product Name");

        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
        productShoppingCart.setCart(cart2);
        productShoppingCart.setId(id);
        productShoppingCart.setOrderQuantity(1);
        productShoppingCart.setProduct(product2);
        productShoppingCart.setProductColor("Product Color");
        productShoppingCart.setProductSize("Product Size");
        when(productShoppingCartRepository.save(Mockito.<ProductShoppingCart>any())).thenReturn(productShoppingCart);

        // Act
        productShoppingCartService.addProductToCart("42", 1);

        // Assert
        verify(customerRepository).findByCustomerId(eq(1));
        verify(productRepository).findByProductId(eq("42"));
        verify(productShoppingCartRepository).save(isA(ProductShoppingCart.class));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#addProductToCart(String, int)}
     */
    @Test
    void testAddProductToCart2()
            throws DatabaseAccessException, InvalidProductDataException, ProductAlreadyExistsException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        Customer customer = new Customer();
        customer.setCart(cart);
        customer.setCountry("GB");
        customer.setCustomerId(1);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customer);

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
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(productShoppingCartRepository.save(Mockito.<ProductShoppingCart>any()))
                .thenThrow(new DuplicateKeyException("Msg"));

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class, () -> productShoppingCartService.addProductToCart("42", 1));
        verify(customerRepository).findByCustomerId(eq(1));
        verify(productRepository).findByProductId(eq("42"));
        verify(productShoppingCartRepository).save(isA(ProductShoppingCart.class));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#addProductToCart(String, int)}
     */
    @Test
    void testAddProductToCart3()
            throws DatabaseAccessException, InvalidProductDataException, ProductAlreadyExistsException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        Customer customer = new Customer();
        customer.setCart(cart);
        customer.setCountry("GB");
        customer.setCustomerId(1);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customer);

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
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(productShoppingCartRepository.save(Mockito.<ProductShoppingCart>any()))
                .thenThrow(new ProductAlreadyExistsException("An error occurred"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productShoppingCartService.addProductToCart("42", 1));
        verify(customerRepository).findByCustomerId(eq(1));
        verify(productRepository).findByProductId(eq("42"));
        verify(productShoppingCartRepository).save(isA(ProductShoppingCart.class));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#addProductToCart(String, int)}
     */
    @Test
    void testAddProductToCart4()
            throws DatabaseAccessException, InvalidProductDataException, ProductAlreadyExistsException {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1);
        cart.setDiscountAmount(new BigDecimal("2.3"));
        cart.setPurchaseStatus(true);
        cart.setTotalAmount(new BigDecimal("2.3"));

        Customer customer = new Customer();
        customer.setCart(cart);
        customer.setCountry("GB");
        customer.setCustomerId(1);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setUsername("janedoe");
        when(customerRepository.findByCustomerId(anyInt())).thenReturn(customer);

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
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(productShoppingCartRepository.save(Mockito.<ProductShoppingCart>any()))
                .thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(DatabaseAccessException.class, () -> productShoppingCartService.addProductToCart("42", 1));
        verify(customerRepository).findByCustomerId(eq(1));
        verify(productRepository).findByProductId(eq("42"));
        verify(productShoppingCartRepository).save(isA(ProductShoppingCart.class));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getTotalAmount(int)}
     */
    @Test
    void testGetTotalAmount() {
        // Arrange
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(1);
        shoppingCart.setDiscountAmount(new BigDecimal("2.3"));
        shoppingCart.setPurchaseStatus(true);
        BigDecimal totalAmount = new BigDecimal("2.3");
        shoppingCart.setTotalAmount(totalAmount);
        when(shoppingCartRepository.findByCartId(anyInt())).thenReturn(shoppingCart);

        // Act
        BigDecimal actualTotalAmount = productShoppingCartService.getTotalAmount(1);

        // Assert
        verify(shoppingCartRepository).findByCartId(eq(1));
        assertEquals(new BigDecimal("2.3"), actualTotalAmount);
        assertSame(totalAmount, actualTotalAmount);
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getTotalAmount(int)}
     */
    @Test
    void testGetTotalAmount2() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt())).thenThrow(new DuplicateKeyException("Msg"));

        // Act and Assert
        assertThrows(DuplicateKeyException.class, () -> productShoppingCartService.getTotalAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getTotalAmount(int)}
     */
    @Test
    void testGetTotalAmount3() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt()))
                .thenThrow(new ProductAlreadyExistsException("An error occurred"));

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class, () -> productShoppingCartService.getTotalAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getTotalAmount(int)}
     */
    @Test
    void testGetTotalAmount4() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt())).thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(EmptyResultDataAccessException.class, () -> productShoppingCartService.getTotalAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getDiscountAmount(int)}
     */
    @Test
    void testGetDiscountAmount() {
        // Arrange
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(1);
        BigDecimal discountAmount = new BigDecimal("2.3");
        shoppingCart.setDiscountAmount(discountAmount);
        shoppingCart.setPurchaseStatus(true);
        shoppingCart.setTotalAmount(new BigDecimal("2.3"));
        when(shoppingCartRepository.findByCartId(anyInt())).thenReturn(shoppingCart);

        // Act
        BigDecimal actualDiscountAmount = productShoppingCartService.getDiscountAmount(1);

        // Assert
        verify(shoppingCartRepository).findByCartId(eq(1));
        assertEquals(new BigDecimal("2.3"), actualDiscountAmount);
        assertSame(discountAmount, actualDiscountAmount);
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getDiscountAmount(int)}
     */
    @Test
    void testGetDiscountAmount2() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt())).thenThrow(new DuplicateKeyException("Msg"));

        // Act and Assert
        assertThrows(DuplicateKeyException.class, () -> productShoppingCartService.getDiscountAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getDiscountAmount(int)}
     */
    @Test
    void testGetDiscountAmount3() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt()))
                .thenThrow(new ProductAlreadyExistsException("An error occurred"));

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class, () -> productShoppingCartService.getDiscountAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test: {@link ProductShoppingCartService#getDiscountAmount(int)}
     */
    @Test
    void testGetDiscountAmount4() {
        // Arrange
        when(shoppingCartRepository.findByCartId(anyInt())).thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(EmptyResultDataAccessException.class, () -> productShoppingCartService.getDiscountAmount(1));
        verify(shoppingCartRepository).findByCartId(eq(1));
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#getProductColorSizeByCartId(int)}
     */
    @Test
    void testGetProductColorSizeByCartId() {
        // Arrange
        ArrayList<ProductColorSize> productColorSizeList = new ArrayList<>();
        when(productShoppingCartRepository.findProductColorSizeByCartId(anyInt())).thenReturn(productColorSizeList);

        // Act
        List<ProductColorSize> actualProductColorSizeByCartId = productShoppingCartService.getProductColorSizeByCartId(1);

        // Assert
        verify(productShoppingCartRepository).findProductColorSizeByCartId(eq(1));
        assertTrue(actualProductColorSizeByCartId.isEmpty());
        assertSame(productColorSizeList, actualProductColorSizeByCartId);
    }

    /**
     * Method under test:
     * {@link ProductShoppingCartService#getProductColorSizeByCartId(int)}
     */
    @Test
    void testGetProductColorSizeByCartId2() {
        // Arrange
        when(productShoppingCartRepository.findProductColorSizeByCartId(anyInt()))
                .thenThrow(new DuplicateKeyException("Msg"));

        // Act and Assert
        assertThrows(DuplicateKeyException.class, () -> productShoppingCartService.getProductColorSizeByCartId(1));
        verify(productShoppingCartRepository).findProductColorSizeByCartId(eq(1));
    }
}
