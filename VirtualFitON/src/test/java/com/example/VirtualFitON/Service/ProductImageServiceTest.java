package com.example.VirtualFitON.Service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.Exceptions.ProductImageNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProductImageService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductImageServiceTest {
    @MockBean
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductImageService productImageService;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test:
     * {@link ProductImageService#getImageDataListByProductId(String)}
     */
    @Test
    void testGetImageDataListByProductId() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<byte[]> actualImageDataListByProductId = productImageService.getImageDataListByProductId("42");

        // Assert
        verify(productImageRepository).findByProductProductId(eq("42"));
        assertTrue(actualImageDataListByProductId.isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductImageService#getImageDataListByProductId(String)}
     */
    @Test
    void testGetImageDataListByProductId2() throws UnsupportedEncodingException {
        // Arrange
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

        ProductImage productImage = new ProductImage();
        productImage.setColour("Colour");
        productImage.setId(1L);
        productImage.setImageData("AXAXAXAX".getBytes("UTF-8"));
        productImage.setProduct(product);

        ArrayList<ProductImage> productImageList = new ArrayList<>();
        productImageList.add(productImage);
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(productImageList);

        // Act
        List<byte[]> actualImageDataListByProductId = productImageService.getImageDataListByProductId("42");

        // Assert
        verify(productImageRepository).findByProductProductId(eq("42"));
        assertEquals(1, actualImageDataListByProductId.size());
        assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualImageDataListByProductId.get(0));
    }

    /**
     * Method under test:
     * {@link ProductImageService#getImageDataListByProductId(String)}
     */
    @Test
    void testGetImageDataListByProductId3() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new ProductImageNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductImageNotFoundException.class, () -> productImageService.getImageDataListByProductId("42"));
        verify(productImageRepository).findByProductProductId(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductImageService#saveProductImage(String, String, MultipartFile)}
     */
    @Test
    void testSaveProductImage() throws IOException {
        // Arrange
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

        ProductImage productImage = new ProductImage();
        productImage.setColour("Colour");
        productImage.setId(1L);
        productImage.setImageData("AXAXAXAX".getBytes("UTF-8"));
        productImage.setProduct(product);
        when(productImageRepository.save(Mockito.<ProductImage>any())).thenReturn(productImage);

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
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product2);

        // Act
        productImageService.saveProductImage("42", "Colour",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(productRepository).findByProductId(eq("42"));
        verify(productImageRepository).save(isA(ProductImage.class));
    }

    /**
     * Method under test:
     * {@link ProductImageService#saveProductImage(String, String, MultipartFile)}
     */
    @Test
    void testSaveProductImage2() throws IOException {
        // Arrange
        when(productRepository.findByProductId(Mockito.<String>any()))
                .thenThrow(new ProductImageNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductImageNotFoundException.class, () -> productImageService.saveProductImage("42", "Colour",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(productRepository).findByProductId(eq("42"));
    }
}
