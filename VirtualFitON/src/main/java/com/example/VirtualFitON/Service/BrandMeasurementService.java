package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Exceptions.BrandNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import com.example.VirtualFitON.Repositories.BrandRepository;

import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandMeasurementService {

    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    @Autowired
    private ProductRepository productRepository;

    public BrandMeasurement saveBrandMeasurement(BrandMeasurement brandMeasurement){
        return brandMeasurementRepository.save(brandMeasurement);
    }

    public List<BrandMeasurement> saveBrandMeasurements(List<BrandMeasurement> measurements){
        return brandMeasurementRepository.saveAll(measurements);
    }

    public List<BrandMeasurement> getAllBrandMeasurement(){
        return brandMeasurementRepository.findAll();
    }

    public BrandMeasurement findBrandMeasurementById(Long id)
    {
        if(brandMeasurementRepository.findById(id).isEmpty())
            throw new BrandNotFoundException("Requested brand measurement doesn't exist!");
        return brandMeasurementRepository.findById(id).get();
    }
    public BrandMeasurement findBrandMeasurementByCategoryItemSize(String category, String item, String size) {
        List<BrandMeasurement> measurements = brandMeasurementRepository.findByCategoryAndItemAndSize(category, item, size);

        if (measurements.isEmpty()) {
            throw new BrandNotFoundException("Requested brand measurement doesn't exist for the given category, item, and size!");
        }

        // Assuming you want to return the first matching measurement if multiple are found
        return measurements.get(0);
    }
    public Map<String, Map<String, Double>> getBrandMeasurementsByBrandId(String productId) {
        Product product = productRepository.findByProductId(productId);

        List<BrandMeasurement> brandMeasurements = brandMeasurementRepository.findByBrand_BrandIdAndCategory(product.getBrand().getBrandId(), product.getProductCategory() );

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

    public String deleteBrandMeasurement(Long id){
        brandMeasurementRepository.deleteById(id);
        return "Brand delete Successful!!" +id;
    }

    public BrandMeasurement updateBrandMeasurement(BrandMeasurement brandMeasurement){
        BrandMeasurement existingBrand = brandMeasurementRepository.findById(brandMeasurement.getMeasurement_id()).orElse(null);
        existingBrand.setAnkleCircumference(brandMeasurement.getAnkleCircumference());
        existingBrand.setBicepCircumference(brandMeasurement.getBicepCircumference());
        existingBrand.setArmLength(brandMeasurement.getArmLength());
        existingBrand.setCalfCircumference(brandMeasurement.getCalfCircumference());
        existingBrand.setChestCircumference(brandMeasurement.getChestCircumference());
        existingBrand.setForearmCircumference(brandMeasurement.getForearmCircumference());
        existingBrand.setHeadCircumference(brandMeasurement.getHeadCircumference());
        existingBrand.setHipCircumference(brandMeasurement.getHipCircumference());
        existingBrand.setInsideLegLength(brandMeasurement.getInsideLegLength());
        existingBrand.setNeckCircumference(brandMeasurement.getNeckCircumference());
        existingBrand.setThighCircumference(brandMeasurement.getThighCircumference());
        existingBrand.setShoulderBreadth(brandMeasurement.getShoulderBreadth());
        existingBrand.setShoulderBreadth(brandMeasurement.getShoulderBreadth());
        existingBrand.setShoulderToCrotch(brandMeasurement.getShoulderToCrotch());
        return brandMeasurementRepository.save(existingBrand);
    }
}
