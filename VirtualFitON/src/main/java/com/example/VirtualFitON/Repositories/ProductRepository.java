package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository <Product,String>{
    Product findByProductId(String productId);

    Set<Product> findByProductCategory(String category);
    Set<Product> findByBrand(Brand brand);
    Set<Product> findByGender(String gender);

    @Query("SELECT DISTINCT product FROM Product product WHERE " +

            "(:minPrice IS NULL OR product.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR product.price <= :maxPrice) ")
    Set<Product> findProductsbyPrice(@Param("minPrice") int minPrice,
                                     @Param("maxPrice") int maxPrice);


    Product findByProductName(String name);

}
