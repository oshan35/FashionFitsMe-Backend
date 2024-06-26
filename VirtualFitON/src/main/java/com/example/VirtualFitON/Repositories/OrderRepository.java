package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderId(Integer orderId);
    @Query("SELECT o.shoppingCart FROM Order o WHERE o.orderId = :orderId")
    ShoppingCart findProductsbyOrderId(Integer orderId);



}
