package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    List<ProductImage>findByProductProductId(String productId);
}
