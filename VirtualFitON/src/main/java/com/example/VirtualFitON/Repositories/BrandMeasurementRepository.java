package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandMeasurementRepository extends JpaRepository<BrandMeasurement, Long> {

    // New method to find by both brandId and category
    List<BrandMeasurement> findByBrand_BrandIdAndCategory(String brandId, String category);
}
