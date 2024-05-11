package com.example.VirtualFitON.Controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import com.example.VirtualFitON.Controllers.ProductController;
import com.example.VirtualFitON.Service.ProductService;
import com.example.VirtualFitON.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @org.junit.jupiter.api.Test
    public void saveProductWithImages_ShouldSaveProduct_WhenValidRequest() throws Exception {
        // Prepare data and mock behavior
        MockMultipartFile firstFile = new MockMultipartFile("imageFiles", "filename1.png", "image/png", "some png".getBytes());
        List<MockMultipartFile> files = new ArrayList<>();
        files.add(firstFile);

//        given(productService.saveProductWithImages(any(), any(), any(), any(), any(), any(), any())).willReturn("Product saved successfully.");

        // Execute and Verify
        mockMvc.perform(MockMvcRequestBuilders.multipart("/products/add-product-with-images")
                        .file(firstFile)
                        .param("productId", "123")
                        .param("productName", "Test Product")
                        .param("price", "100")
                        .param("productCategory", "Category")
                        .param("gender", "Unisex")
                        .param("brand", "BrandX")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().string("Product saved successfully."));
    }

    @org.junit.jupiter.api.Test
    public void saveProductWithImages_ShouldReturnBadRequest_WhenInvalidData() throws Exception {
        // Mocking ProductService to throw an InvalidProductDataException
//        given(productService.saveProductWithImages(any(), any(), any(), any(), any(), any(), any())).willThrow(new InvalidProductDataException("Invalid data"));

        // Execute and verify
        mockMvc.perform(post("/products/add-product-with-images")
                        .param("productId", "123")
                        .param("productName", "Test Product")
                        .param("price", "100")
                        .param("productCategory", "Category")
                        .param("gender", "Unisex")
                        .param("brand", "BrandX")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid data"));
    }
}
