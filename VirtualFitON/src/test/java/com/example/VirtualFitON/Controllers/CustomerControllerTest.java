package com.example.VirtualFitON.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.CustomerRegisterDTO;
import com.example.VirtualFitON.DTO.LoginDTO;
import com.example.VirtualFitON.DTO.SignUpDTO;
import com.example.VirtualFitON.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class, RedisTemplate.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private RedisTemplate redisTemplate;

    /**
     * Method under test:
     * {@link CustomerController#getCustomerId(HttpServletRequest)}
     */
    @Test
    void testGetCustomerId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();

        // Act
        ResponseEntity<String> actualCustomerId = customerController.getCustomerId(new MockHttpServletRequest());

        // Assert
        assertEquals("Missing session ID", actualCustomerId.getBody());
        assertEquals(400, actualCustomerId.getStatusCodeValue());
        assertTrue(actualCustomerId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#getCustomerId(HttpServletRequest)}
     */
    @Test
    void testGetCustomerId2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");

        // Act
        ResponseEntity<String> actualCustomerId = customerController.getCustomerId(request);

        // Assert
        verify(request).getHeader(eq("Authorization"));
        assertEquals("Missing session ID", actualCustomerId.getBody());
        assertEquals(400, actualCustomerId.getStatusCodeValue());
        assertTrue(actualCustomerId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#registerUser(CustomerRegisterDTO)}
     */
    @Test
    void testRegisterUser() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();

        // Act
        ResponseEntity<String> actualRegisterUserResult = customerController
                .registerUser(new CustomerRegisterDTO("Jane", "Doe", "GB", "janedoe", "iloveyou"));

        // Assert
        assertEquals(
                "Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.registerCustomer(com.example.VirtualFitON"
                        + ".DTO.CustomerRegisterDTO)\" because \"this.customerService\" is null",
                actualRegisterUserResult.getBody());
        assertEquals(500, actualRegisterUserResult.getStatusCodeValue());
        assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#registerUser(CustomerRegisterDTO)}
     */
    @Test
    void testRegisterUser2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<String> actualRegisterUserResult = (new CustomerController())
                .registerUser(mock(CustomerRegisterDTO.class));

        // Assert
        assertEquals(
                "Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.registerCustomer(com.example.VirtualFitON"
                        + ".DTO.CustomerRegisterDTO)\" because \"this.customerService\" is null",
                actualRegisterUserResult.getBody());
        assertEquals(500, actualRegisterUserResult.getStatusCodeValue());
        assertTrue(actualRegisterUserResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CustomerController#signUpUser(SignUpDTO)}
     */
    @Test
    void testSignUpUser() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();

        // Act
        ResponseEntity<String> actualSignUpUserResult = customerController
                .signUpUser(new SignUpDTO("Jane", "Doe", "janedoe", "iloveyou"));

        // Assert
        assertEquals(
                "Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.signUpCustomer(com.example.VirtualFitON"
                        + ".DTO.SignUpDTO)\" because \"this.customerService\" is null",
                actualSignUpUserResult.getBody());
        assertEquals(500, actualSignUpUserResult.getStatusCodeValue());
        assertTrue(actualSignUpUserResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CustomerController#signUpUser(SignUpDTO)}
     */
    @Test
    void testSignUpUser2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<String> actualSignUpUserResult = (new CustomerController()).signUpUser(mock(SignUpDTO.class));

        // Assert
        assertEquals(
                "Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.signUpCustomer(com.example.VirtualFitON"
                        + ".DTO.SignUpDTO)\" because \"this.customerService\" is null",
                actualSignUpUserResult.getBody());
        assertEquals(500, actualSignUpUserResult.getStatusCodeValue());
        assertTrue(actualSignUpUserResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#loginCustomer(LoginDTO, HttpServletRequest)}
     */
    @Test
    void testLoginCustomer() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();
        LoginDTO loginDTO = new LoginDTO("janedoe", "iloveyou");

        // Act
        ResponseEntity<?> actualLoginCustomerResult = customerController.loginCustomer(loginDTO,
                new MockHttpServletRequest());

        // Assert
        assertEquals(
                "Error during login: Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.LoginCustomer(com"
                        + ".example.VirtualFitON.DTO.LoginDTO)\" because \"this.customerService\" is null",
                actualLoginCustomerResult.getBody());
        assertEquals(500, actualLoginCustomerResult.getStatusCodeValue());
        assertTrue(actualLoginCustomerResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#loginCustomer(LoginDTO, HttpServletRequest)}
     */
    @Test
    void testLoginCustomer2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CustomerController customerController = new CustomerController();
        LoginDTO loginDTO = mock(LoginDTO.class);

        // Act
        ResponseEntity<?> actualLoginCustomerResult = customerController.loginCustomer(loginDTO,
                new MockHttpServletRequest());

        // Assert
        assertEquals(
                "Error during login: Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.LoginCustomer(com"
                        + ".example.VirtualFitON.DTO.LoginDTO)\" because \"this.customerService\" is null",
                actualLoginCustomerResult.getBody());
        assertEquals(500, actualLoginCustomerResult.getStatusCodeValue());
        assertTrue(actualLoginCustomerResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#deleteCartItemByCustomer(int, String)}
     */
    @Test
    void testDeleteCartItemByCustomer() throws Exception {
        // Arrange
        when(customerService.deleteCartItem(anyInt(), Mockito.<String>any())).thenReturn("Delete Cart Item");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/customer/cart/{customerId}/remove/{productId}", 1, "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Cart Item"));
    }

    /**
     * Method under test: {@link CustomerController#getCartItems(int)}
     */
    @Test
    void testGetCartItems() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualCartItems = (new CustomerController()).getCartItems(1);

        // Assert
        assertEquals(
                "Unexpected error: Cannot invoke \"com.example.VirtualFitON.Service.CustomerService.getCustomerCartItems(int)\""
                        + " because \"this.customerService\" is null",
                actualCartItems.getBody());
        assertEquals(500, actualCartItems.getStatusCodeValue());
        assertTrue(actualCartItems.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CustomerController#deleteCartItemByCustomer(int, String)}
     */
    @Test
    void testDeleteCartItemByCustomer2() throws Exception {
        // Arrange
        when(customerService.deleteCartItem(anyInt(), Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/customer/cart/{customerId}/remove/{productId}", 1, "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid customer ID"));
    }

    /**
     * Method under test:
     * {@link CustomerController#deleteCartItemByCustomer(int, String)}
     */
    @Test
    void testDeleteCartItemByCustomer3() throws Exception {
        // Arrange
        when(customerService.deleteCartItem(anyInt(), Mockito.<String>any())).thenThrow(new SecurityException("foo"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/customer/cart/{customerId}/remove/{productId}", 1, "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Access denied"));
    }

    /**
     * Method under test: {@link CustomerController#getCartItems(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCartItems2() throws Exception {

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/cart/{customerId}", 1);

        // Act
        MockMvcBuilders.standaloneSetup(customerController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link CustomerController#getCustomerId(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomerId3() throws Exception {


        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/getCustomerId");

        // Act
        MockMvcBuilders.standaloneSetup(customerController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link CustomerController#loginCustomer(LoginDTO, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoginCustomer3() throws Exception {

        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword("iloveyou");
        loginDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(customerController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link CustomerController#registerUser(CustomerRegisterDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterUser3() throws Exception {

        // Arrange
        CustomerRegisterDTO customerRegisterDTO = new CustomerRegisterDTO("Jane", "Doe", "GB", "janedoe", "iloveyou");
        customerRegisterDTO.setFirstName(null);
        customerRegisterDTO.setLastName(null);
        customerRegisterDTO.setCountry(null);
        customerRegisterDTO.setPassword(null);
        customerRegisterDTO.setUsername(null);
        String content = (new ObjectMapper()).writeValueAsString(customerRegisterDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(customerController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link CustomerController#signUpUser(SignUpDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignUpUser3() throws Exception {

        // Arrange
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setFirstName("Jane");
        signUpDTO.setLastName("Doe");
        signUpDTO.setPassword("iloveyou");
        signUpDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(signUpDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(customerController).build().perform(requestBuilder);
    }
}
