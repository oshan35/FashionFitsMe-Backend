package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Exceptions.BrandNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import com.example.VirtualFitON.Repositories.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandMeasurementService {

    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    public List<BrandMeasurement> getMeasurements(String brandId, String category, String item, String size) {
        return brandMeasurementRepository.findByBrandIdAndCategoryAndItemAndSize(brandId, category, item, size);
    }
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


    public String deleteBrandMeasurement(Long id){
        brandMeasurementRepository.deleteById(id);
        return "Brand delete Successful!!" +id;
    }

    @Autowired
    public BrandMeasurementService(BrandMeasurementRepository measurementRepository) {
        this.brandMeasurementRepository = measurementRepository;
    }

    public BrandMeasurement updateBrandMeasurement(Long measurementId, BrandMeasurement updatedMeasurement) {
        BrandMeasurement existingMeasurement = brandMeasurementRepository.findById(measurementId)
                .orElseThrow(() -> new IllegalArgumentException("Measurement with id " + measurementId + " not found"));

        // Update properties that you want to allow modification
        existingMeasurement.setAnkleCircumference(updatedMeasurement.getAnkleCircumference());
        existingMeasurement.setArmLength(updatedMeasurement.getArmLength());
        existingMeasurement.setBicepCircumference(updatedMeasurement.getBicepCircumference());
        existingMeasurement.setShoulderToCrotch(updatedMeasurement.getShoulderToCrotch());
        existingMeasurement.setShoulderBreadth(updatedMeasurement.getShoulderBreadth());
        existingMeasurement.setCalfCircumference(updatedMeasurement.getCalfCircumference());
        existingMeasurement.setChestCircumference(updatedMeasurement.getChestCircumference());
        existingMeasurement.setForearmCircumference(updatedMeasurement.getForearmCircumference());
        existingMeasurement.setHeadCircumference(updatedMeasurement.getHeadCircumference());
        existingMeasurement.setHipCircumference(updatedMeasurement.getHipCircumference());
        existingMeasurement.setInsideLegLength(updatedMeasurement.getInsideLegLength());
        existingMeasurement.setNeckCircumference(updatedMeasurement.getNeckCircumference());
        existingMeasurement.setThighCircumference(updatedMeasurement.getThighCircumference());
        existingMeasurement.setWaistCircumference(updatedMeasurement.getWaistCircumference());
        existingMeasurement.setWristCircumference(updatedMeasurement.getWristCircumference());
        return brandMeasurementRepository.save(existingMeasurement);
    }

}
