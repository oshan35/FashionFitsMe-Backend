package com.example.VirtualFitON.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.Service.ProductImageService;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProductImageController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductImageControllerTest {
    @Autowired
    private ProductImageController productImageController;

    @MockBean
    private ProductImageService productImageService;

    /**
     * Method under test:
     * {@link ProductImageController#addProductImages(String, String, MultipartFile)}
     */
    @Test
    void testAddProductImages() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProductImageController productImageController = new ProductImageController();

        // Act
        ResponseEntity<?> actualAddProductImagesResult = productImageController.addProductImages("42", "Colour",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        assertNull(actualAddProductImagesResult.getBody());
        assertEquals(500, actualAddProductImagesResult.getStatusCodeValue());
        assertTrue(actualAddProductImagesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductImageController#addProductImages(String, String, MultipartFile)}
     */
    @Test
    void testAddProductImages2() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProductImageController productImageController = new ProductImageController();
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();

        // Act
        ResponseEntity<?> actualAddProductImagesResult = productImageController.addProductImages("42", "Colour",
                new MockMultipartFile("Name", contentStream));

        // Assert
        verify(contentStream).close();
        verify(contentStream).readAllBytes();
        assertNull(actualAddProductImagesResult.getBody());
        assertEquals(500, actualAddProductImagesResult.getStatusCodeValue());
        assertTrue(actualAddProductImagesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductImageController#getProductImagesByProductId(String)}
     */
    @Test
    void testGetProductImagesByProductId() throws Exception {
        // Arrange
        when(productImageService.getImageDataListByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product-images/getImage/{Id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productImageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
