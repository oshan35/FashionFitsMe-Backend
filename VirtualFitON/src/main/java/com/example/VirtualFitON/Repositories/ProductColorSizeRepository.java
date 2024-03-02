package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.ProductColorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductColorSizeRepository extends JpaRepository<ProductColorSize, String> {

    @Query("SELECT pcs FROM ProductColorSize pcs WHERE pcs.product.productId = :productId AND pcs.id.color = :color AND pcs.id.size= :size")
    ProductColorSize findByProductIdAndColorAndSize(String productId, String color, String size);


    @Query("SELECT pcs FROM ProductColorSize pcs WHERE " +
            "(:color IS NULL OR pcs.id.color = :color) AND " +
            "(:size IS NULL OR pcs.id.size = :size) AND " +
            "(:quantity IS NULL OR pcs.quantity >= :quantity) AND " +
            "(:categories IS NULL OR pcs.product.productCategory = :categories) AND " +
            "(:brand IS NULL OR pcs.product.brand.brandName = :brand) AND " +
            "(:minPrice IS NULL OR pcs.product.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR pcs.product.price <= :maxPrice) AND " +
            "(:gender IS NULL OR pcs.product.gender = :gender)")
    List<ProductColorSize> findFilteredProductColorSize(@Param("color") String color,
                                                        @Param("size") String size,
                                                        @Param("quantity") Integer quantity,
                                                        @Param("categories") String categories,
                                                        @Param("brand") String brand,
                                                        @Param("minPrice") BigDecimal minPrice,
                                                        @Param("maxPrice") BigDecimal maxPrice,
                                                        @Param("gender") String gender);



}

