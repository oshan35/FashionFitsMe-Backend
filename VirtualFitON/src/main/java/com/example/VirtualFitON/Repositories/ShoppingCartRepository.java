package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findByCartId(String cartId);

    @Query("SELECT sc FROM ShoppingCart sc")
    ShoppingCart getAllCarts();
}
