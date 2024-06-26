package com.example.VirtualFitON.Repositories;


import com.example.VirtualFitON.Models.BrandMeasurement;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BrandMeasurementRepository extends JpaRepository <BrandMeasurement, Long> {

    List<BrandMeasurement> findByCategoryAndItemAndSize(String category, String item, String size);
    List<BrandMeasurement> findByBrandBrandId(String brandId);

    @Query("SELECT bm FROM BrandMeasurement bm WHERE bm.brand.brandId = :brandId AND bm.category = :category AND bm.item = :item AND bm.size = :size")
    List<BrandMeasurement> findByBrandIdAndCategoryAndItemAndSize(
            @Param("brandId") String brandId,
            @Param("category") String category,
            @Param("item") String item,
            @Param("size") String size
    );

}