package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository  extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);

    @Query("SELECT cus.cart.cartId FROM Customer cus WHERE cus.customerId = :customerId")
    int findCartId(int customerId);

    Customer findByCustomerId(int customerId);

}

