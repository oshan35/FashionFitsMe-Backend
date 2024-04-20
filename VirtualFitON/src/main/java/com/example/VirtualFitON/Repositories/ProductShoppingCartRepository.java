package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductShoppingCartRepository extends JpaRepository<ProductShoppingCart, ProductShoppingCartId> {
    @Query("SELECT psc.product FROM ProductShoppingCart psc WHERE psc.cart.cartId = :cartId")
    List<Product> findProductsByCartId(@Param("cartId") int cartId);

    @Query("SELECT pci FROM ProductShoppingCart pci WHERE pci.cart.cartId = :cartId")
    List<ProductShoppingCart> findCartProductsByCartId(@Param("cartId") int cartId);

    @Query("SELECT p FROM Product p WHERE p.productId = :Id")
    Product findProductsByProduct_Id( @Param("Id")String Id);

    @Query("SELECT pcs FROM ProductColorSize pcs " +
            "JOIN ProductShoppingCart psc ON pcs.id.productId = psc.id.productId " +
            "WHERE psc.id.cartId = :cartId")
    List<ProductColorSize> findProductColorSizeByCartId(@Param("cartId") int cartId);


    @Modifying
    @Transactional
    @Query("DELETE FROM ProductShoppingCart p WHERE p.id.productId = :productId AND p.id.cartId = :cartId")
    void deleteByProductIdAndCartId(String productId, int cartId);
}
