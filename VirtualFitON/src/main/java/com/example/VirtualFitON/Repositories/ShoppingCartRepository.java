package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findByCartId(String cartId);
}
