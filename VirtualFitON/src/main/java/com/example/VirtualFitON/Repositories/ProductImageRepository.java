package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    List<ProductImage>findByProductProductId(String productId);


    @Query("SELECT pi.imageData FROM ProductImage pi WHERE pi.product.productId = :productId AND pi.colour = :color ")
    byte[] findByProductIdAndColor(String productId, String color);
}
