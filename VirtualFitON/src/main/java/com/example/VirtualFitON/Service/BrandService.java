package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.BrandDetailsDTO;
import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Exceptions.BrandNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import com.example.VirtualFitON.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    public BrandDetailsDTO getBrandDetails(String brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + brandId));

        List<BrandMeasurement> measurements = brandMeasurementRepository.findByBrandBrandId(brandId);

        BrandDetailsDTO brandDetailsDTO = new BrandDetailsDTO();
        brandDetailsDTO.setBrandId(brand.getBrandId());
        brandDetailsDTO.setBrandName(brand.getBrandName());
        brandDetailsDTO.setProductMedia(brand.getProductMedia());
        brandDetailsDTO.setMeasurements(measurements.stream().map(this::convertToDTO).collect(Collectors.toList()));

        return brandDetailsDTO;
    }

    private BrandMeasurementDTO convertToDTO(BrandMeasurement measurement) {
        BrandMeasurementDTO dto = new BrandMeasurementDTO();
        dto.setMeasurementId(measurement.getMeasurement_id());
        dto.setAnkleCircumference(measurement.getAnkleCircumference());
        dto.setArmLength(measurement.getArmLength());
        dto.setBicepCircumference(measurement.getBicepCircumference());
        dto.setCalfCircumference(measurement.getCalfCircumference());
        dto.setCategory(measurement.getCategory());
        dto.setChestCircumference(measurement.getChestCircumference());
        dto.setForearmCircumference(measurement.getForearmCircumference());
        dto.setHeadCircumference(measurement.getHeadCircumference());
        dto.setHipCircumference(measurement.getHipCircumference());
        dto.setInsideLegLength(measurement.getInsideLegLength());
        dto.setItem(measurement.getItem());
        dto.setNeckCircumference(measurement.getNeckCircumference());
        dto.setShoulderBreadth(measurement.getShoulderBreadth());
        dto.setShoulderToCrotch(measurement.getShoulderToCrotch());
        dto.setSize(measurement.getSize());
        dto.setThighCircumference(measurement.getThighCircumference());
        dto.setWaistCircumference(measurement.getWaistCircumference());
        dto.setWristCircumference(measurement.getWristCircumference());
        return dto;
    }



    public Brand saveBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> saveBrands(List<Brand> brands){
        return brandRepository.saveAll(brands);
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brand findBrandById(String id)
    {
        if(brandRepository.findById(id).isEmpty())
            throw new BrandNotFoundException("Requested brand doesn't exist!");
        return brandRepository.findById(id).get();
    }

    public Brand findBrandByName(String name){
        return brandRepository.findByBrandName(name);
    }

    public String deleteBrand(String id){
        brandRepository.deleteById(id);
        return "Brand delete Successful!!" +id;
    }

    public Brand updateBrand(Brand brand){
        Brand exixtingBrand = brandRepository.findById(brand.getBrandId()).orElse(null);
        exixtingBrand.setBrandName(brand.getBrandName());
        exixtingBrand.setProductMedia(brand.getProductMedia());
        return brandRepository.save(exixtingBrand);
    }
}
