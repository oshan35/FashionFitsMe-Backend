package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductShoppingCartRepository productShoppingCartRepository;

    @Transactional
    public void clearCart(int customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        ShoppingCart cart = customer.getCart();

        if (cart != null) {
            productShoppingCartRepository.deleteByCart(cart);

            cart.setTotalAmount(BigDecimal.ZERO);
            cart.setPurchaseStatus(false);
            cart.setDiscountAmount(BigDecimal.ZERO);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Cart not found for customer ID: " + customerId);
        }
    }
}
