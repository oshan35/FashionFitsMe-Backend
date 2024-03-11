package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);
}

