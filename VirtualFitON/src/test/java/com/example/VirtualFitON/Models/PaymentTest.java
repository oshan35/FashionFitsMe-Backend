package com.example.VirtualFitON.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class PaymentTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Payment#setOrder(Order)}
     *   <li>{@link Payment#setPaymentDetails(String)}
     *   <li>{@link Payment#setPaymentMethod(String)}
     *   <li>{@link Payment#setPaymentNo(String)}
     *   <li>{@link Payment#getOrder()}
     *   <li>{@link Payment#getPaymentDetails()}
     *   <li>{@link Payment#getPaymentMethod()}
     *   <li>{@link Payment#getPaymentNo()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Payment payment = new Payment();

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

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(1);
        shoppingCart.setDiscountAmount(new BigDecimal("2.3"));
        shoppingCart.setPurchaseStatus(true);
        shoppingCart.setTotalAmount(new BigDecimal("2.3"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        order.setOrderId("42");
        order.setShipment(new Shipment());
        order.setShoppingCart(shoppingCart);
        order.setTotal(1);

        // Act
        payment.setOrder(order);
        payment.setPaymentDetails("Payment Details");
        payment.setPaymentMethod("Payment Method");
        payment.setPaymentNo("Payment No");
        Order actualOrder = payment.getOrder();
        String actualPaymentDetails = payment.getPaymentDetails();
        String actualPaymentMethod = payment.getPaymentMethod();

        // Assert that nothing has changed
        assertEquals("Payment Details", actualPaymentDetails);
        assertEquals("Payment Method", actualPaymentMethod);
        assertEquals("Payment No", payment.getPaymentNo());
        assertSame(order, actualOrder);
    }
}
