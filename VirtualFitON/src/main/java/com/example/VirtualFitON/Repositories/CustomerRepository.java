package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository  extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);

    @Query("SELECT cus.cartId FROM Customer cus WHERE cus.customerId = :customerId")
    String findCartId(int customerId);
}

