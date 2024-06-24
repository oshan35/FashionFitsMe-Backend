package com.example.VirtualFitON.Repositories;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMeasurementRepository extends JpaRepository<CustomerMeasurement, Long> {
    // Custom query methods if needed
    CustomerMeasurement findByCustomerCustomerId(int customerId);
}

