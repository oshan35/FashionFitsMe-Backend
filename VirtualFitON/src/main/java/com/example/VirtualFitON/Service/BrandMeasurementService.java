package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandMeasurementService {

    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    @Autowired
    private ProductRepository productRepository;

    public Map<String, Map<String, Double>> getBrandMeasurementsByBrandId(String productId) {
        Product product = productRepository.findByProductId(productId);

        List<BrandMeasurement> brandMeasurements = brandMeasurementRepository.findByBrand_BrandIdAndCategory(product.getBrand().getBrandId(), product.getProductCategory() );
        // Initialize the map to hold the measurements

        Map<String, Map<String, Double>> measurements = new HashMap<>();


        // Loop through each measurement and add it to the map
        for (BrandMeasurement bm : brandMeasurements) {
            Map<String, Double> sizeMeasurements = new HashMap<>();
            sizeMeasurements.put("ankle_circumference", bm.getAnkleCircumference());
            sizeMeasurements.put("arm_length", bm.getArmLength());
            sizeMeasurements.put("bicep_circumference", bm.getBicepCircumference());
            sizeMeasurements.put("calf_circumference", bm.getCalfCircumference());
            sizeMeasurements.put("chest_circumference", bm.getChestCircumference());
            sizeMeasurements.put("forearm_circumference", bm.getForearmCircumference());
            sizeMeasurements.put("head_circumference", bm.getHeadCircumference());
            sizeMeasurements.put("hip_circumference", bm.getHipCircumference());
            sizeMeasurements.put("inside_leg_length", bm.getInsideLegLength());
            sizeMeasurements.put("neck_circumference", bm.getNeckCircumference());
            sizeMeasurements.put("shoulder_breadth", bm.getShoulderBreadth());
            sizeMeasurements.put("shoulder_to_crotch", bm.getShoulderToCrotch());
            sizeMeasurements.put("thigh_circumference", bm.getThighCircumference());
            sizeMeasurements.put("waist_circumference", bm.getWaistCircumference());
            sizeMeasurements.put("wrist_circumference", bm.getWristCircumference());

            measurements.put(bm.getSize(), sizeMeasurements);
        }

        return measurements;
    }

}
