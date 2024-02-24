package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,String>{
    Product findByProductId(String productId);




}
