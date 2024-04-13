package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);
    Customer findByCustomerId(int customerId);


}

