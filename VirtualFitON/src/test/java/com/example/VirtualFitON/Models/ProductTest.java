package com.example.VirtualFitON.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Product#Product()}
     *   <li>{@link Product#setBrand(Brand)}
     *   <li>{@link Product#setGender(String)}
     *   <li>{@link Product#setPrice(BigDecimal)}
     *   <li>{@link Product#setProductCategory(String)}
     *   <li>{@link Product#setProductId(String)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#getBrand()}
     *   <li>{@link Product#getGender()}
     *   <li>{@link Product#getPrice()}
     *   <li>{@link Product#getProductCategory()}
     *   <li>{@link Product#getProductId()}
     *   <li>{@link Product#getProductName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Product actualProduct = new Product();
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");
        actualProduct.setBrand(brand);
        actualProduct.setGender("Gender");
        BigDecimal price = new BigDecimal("2.3");
        actualProduct.setPrice(price);
        actualProduct.setProductCategory("Product Category");
        actualProduct.setProductId("42");
        actualProduct.setProductName("Product Name");
        Brand actualBrand = actualProduct.getBrand();
        String actualGender = actualProduct.getGender();
        BigDecimal actualPrice = actualProduct.getPrice();
        String actualProductCategory = actualProduct.getProductCategory();
        String actualProductId = actualProduct.getProductId();

        // Assert that nothing has changed
        assertEquals("42", actualProductId);
        assertEquals("Gender", actualGender);
        assertEquals("Product Category", actualProductCategory);
        assertEquals("Product Name", actualProduct.getProductName());
        assertEquals(new BigDecimal("2.3"), actualPrice);
        assertSame(brand, actualBrand);
        assertSame(price, actualPrice);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>
     * {@link Product#Product(String, Brand, String, BigDecimal, String, String)}
     *   <li>{@link Product#setBrand(Brand)}
     *   <li>{@link Product#setGender(String)}
     *   <li>{@link Product#setPrice(BigDecimal)}
     *   <li>{@link Product#setProductCategory(String)}
     *   <li>{@link Product#setProductId(String)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#getBrand()}
     *   <li>{@link Product#getGender()}
     *   <li>{@link Product#getPrice()}
     *   <li>{@link Product#getProductCategory()}
     *   <li>{@link Product#getProductId()}
     *   <li>{@link Product#getProductName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        // Act
        Product actualProduct = new Product("42", brand, "Product Name", new BigDecimal("2.3"), "Product Category",
                "Gender");
        Brand brand2 = new Brand();
        brand2.setBrandId("42");
        brand2.setBrandName("Brand Name");
        actualProduct.setBrand(brand2);
        actualProduct.setGender("Gender");
        BigDecimal price = new BigDecimal("2.3");
        actualProduct.setPrice(price);
        actualProduct.setProductCategory("Product Category");
        actualProduct.setProductId("42");
        actualProduct.setProductName("Product Name");
        Brand actualBrand = actualProduct.getBrand();
        String actualGender = actualProduct.getGender();
        BigDecimal actualPrice = actualProduct.getPrice();
        String actualProductCategory = actualProduct.getProductCategory();
        String actualProductId = actualProduct.getProductId();

        // Assert that nothing has changed
        assertEquals("42", actualProductId);
        assertEquals("Gender", actualGender);
        assertEquals("Product Category", actualProductCategory);
        assertEquals("Product Name", actualProduct.getProductName());
        assertEquals(new BigDecimal("2.3"), actualPrice);
        assertSame(brand2, actualBrand);
        assertSame(price, actualPrice);
    }
}
