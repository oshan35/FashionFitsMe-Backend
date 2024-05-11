package com.example.VirtualFitON.Controllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.example.VirtualFitON.Exceptions.InvalidColorException;
import com.example.VirtualFitON.Service.ProductColorSizeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductColorSizeController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductColorSizeControllerTest {
    @Autowired
    private ProductColorSizeController productColorSizeController;

    @MockBean
    private ProductColorSizeService productColorSizeService;

    /**
     * Method under test:
     * {@link ProductColorSizeController#addProductInformation(String, String, String, int)}
     */
    @Test
    void testAddProductInformation() throws Exception {
        // Arrange
        doNothing().when(productColorSizeService)
                .saveProductInformation(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), anyInt());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.put("/add-product-information")
                .param("color", "foo")
                .param("productId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("quantity", String.valueOf(1))
                .param("size", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productColorSizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product color size information saved successfully."));
    }

    /**
     * Method under test:
     * {@link ProductColorSizeController#addProductInformation(String, String, String, int)}
     */
    @Test
    void testAddProductInformation2() throws Exception {
        // Arrange
        doThrow(new InvalidColorException("An error occurred")).when(productColorSizeService)
                .saveProductInformation(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), anyInt());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.put("/add-product-information")
                .param("color", "foo")
                .param("productId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("quantity", String.valueOf(1))
                .param("size", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productColorSizeController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Validation error: An error occurred"));
    }
}
