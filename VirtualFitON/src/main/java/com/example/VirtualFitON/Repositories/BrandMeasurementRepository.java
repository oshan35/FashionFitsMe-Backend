package com.example.VirtualFitON.Repositories;


import com.example.VirtualFitON.Models.BrandMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BrandMeasurementRepository extends JpaRepository <BrandMeasurement, Long> {

    List<BrandMeasurement> findByCategoryAndItemAndSize(String category, String item, String size);
}