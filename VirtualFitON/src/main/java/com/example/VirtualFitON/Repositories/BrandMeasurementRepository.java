package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandMeasurementRepository extends JpaRepository<CustomerMeasurement, Long> {

}
