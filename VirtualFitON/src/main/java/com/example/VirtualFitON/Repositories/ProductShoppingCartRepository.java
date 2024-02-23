package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductShoppingCartRepository extends JpaRepository<ProductShoppingCart, ProductShoppingCartId> {
    @Query("SELECT p FROM Product p JOIN ProductShoppingCart pc ON p.productId = pc.id.productId WHERE pc.id.cartId = :cartId")
    List<Product> findProductsByCartId(@Param("cartId") String cartId);


    @Query("SELECT p FROM Product p WHERE p.productId = :Id")
    Product findProductsByProduct_Id( @Param("Id")String Id);


}
