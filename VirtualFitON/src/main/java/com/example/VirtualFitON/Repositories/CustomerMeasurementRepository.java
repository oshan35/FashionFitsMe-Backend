package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMeasurementRepository extends JpaRepository<CustomerMeasurement, Long> {
    // Custom query methods if needed
}

