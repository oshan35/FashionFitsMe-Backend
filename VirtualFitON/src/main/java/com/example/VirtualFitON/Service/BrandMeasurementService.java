package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BrandMeasurementService {

    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    public Map<Map<String, Double>> getBrandSizeMeasurementsObject(String brandId){
        
    }

}
