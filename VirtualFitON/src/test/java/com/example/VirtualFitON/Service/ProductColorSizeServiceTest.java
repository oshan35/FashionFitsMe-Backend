package com.example.VirtualFitON.Service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductColorSizeService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductColorSizeServiceTest {
    @MockBean
    private ProductColorSizeRepository productColorSizeRepository;

    @Autowired
    private ProductColorSizeService productColorSizeService;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test:
     * {@link ProductColorSizeService#saveProductInformation(String, String, String, int)}
     */
    @Test
    void testSaveProductInformation() {
        // Arrange
        when(productColorSizeRepository.findByProductIdAndColorAndSize(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<String>any())).thenThrow(new IllegalArgumentException("L"));

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

        // Act
        productColorSizeService.saveProductInformation("42", "Color", "L", 1);

        // Assert that nothing has changed
        verify(productColorSizeRepository).findByProductIdAndColorAndSize(eq("42"), eq("Color"), eq("L"));
        verify(productRepository).findByProductId(eq("42"));
    }
}
