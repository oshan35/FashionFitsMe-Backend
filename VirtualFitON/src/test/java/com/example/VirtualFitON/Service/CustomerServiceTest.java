package com.example.VirtualFitON.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.CustomerRegisterDTO;
import com.example.VirtualFitON.DTO.LoginDTO;
import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.DTO.SignUpDTO;
import com.example.VirtualFitON.Exceptions.UsernameAlreadyExistsException;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class, PasswordEncoder.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ProductShoppingCartRepository productShoppingCartRepository;

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @MockBean
    private ShoppingCartService shoppingCartService;

    /**
     * Method under test:
     * {@link CustomerService#authenticateCustomer(LoginRequestDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticateCustomer() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4ee949cf testClass = com.example.VirtualFitON.Service.DiffblueFakeClass243, locations = [], classes = [com.example.VirtualFitON.Service.CustomerService, org.springframework.security.crypto.password.PasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2d96b20d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5051dacb, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@1ee2b028, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@46d352d5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setPassword("iloveyou");
        loginRequestDto.setUsername("janedoe");

        // Act
        customerService.authenticateCustomer(loginRequestDto);
    }

    /**
     * Method under test:
     * {@link CustomerService#registerCustomer(CustomerRegisterDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterCustomer() throws UsernameAlreadyExistsException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@21f7442a testClass = com.example.VirtualFitON.Service.DiffblueFakeClass357, locations = [], classes = [com.example.VirtualFitON.Service.CustomerService, org.springframework.security.crypto.password.PasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2d96b20d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5051dacb, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@1ee2b028, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@46d352d5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        customerService.registerCustomer(new CustomerRegisterDTO("Jane", "Doe", "GB", "janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link CustomerService#signUpCustomer(SignUpDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignUpCustomer() throws UsernameAlreadyExistsException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@643d51 testClass = com.example.VirtualFitON.Service.DiffblueFakeClass389, locations = [], classes = [com.example.VirtualFitON.Service.CustomerService, org.springframework.security.crypto.password.PasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2d96b20d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5051dacb, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@1ee2b028, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@46d352d5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        customerService.signUpCustomer(new SignUpDTO("Jane", "Doe", "janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link CustomerService#LoginCustomer(LoginDTO)}
     */
    @Test
    void testLoginCustomer() {
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
        when(customerRepository.findByUsername(Mockito.<String>any())).thenReturn(customer);

        // Act
        Customer actualLoginCustomerResult = customerService.LoginCustomer(new LoginDTO("janedoe", "iloveyou"));

        // Assert
        verify(customerRepository).findByUsername(eq("janedoe"));
        assertSame(customer, actualLoginCustomerResult);
    }

    /**
     * Method under test: {@link CustomerService#LoginCustomer(LoginDTO)}
     */
    @Test
    void testLoginCustomer2() {
        // Arrange
        when(customerRepository.findByUsername(Mockito.<String>any()))
                .thenThrow(new UsernameAlreadyExistsException("An error occurred"));

        // Act and Assert
        assertThrows(UsernameAlreadyExistsException.class,
                () -> customerService.LoginCustomer(new LoginDTO("janedoe", "iloveyou")));
        verify(customerRepository).findByUsername(eq("janedoe"));
    }

    /**
     * Method under test: {@link CustomerService#getCustomerCartItems(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomerCartItems() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7c1bbfd1 testClass = com.example.VirtualFitON.Service.DiffblueFakeClass342, locations = [], classes = [com.example.VirtualFitON.Service.CustomerService, org.springframework.security.crypto.password.PasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2d96b20d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5051dacb, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@1ee2b028, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@46d352d5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        customerService.getCustomerCartItems(1);
    }

    /**
     * Method under test: {@link CustomerService#deleteCartItem(int, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCartItem() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5974217a testClass = com.example.VirtualFitON.Service.DiffblueFakeClass325, locations = [], classes = [com.example.VirtualFitON.Service.CustomerService, org.springframework.security.crypto.password.PasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2d96b20d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5051dacb, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@1ee2b028, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@46d352d5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        customerService.deleteCartItem(1, "42");
    }
}
