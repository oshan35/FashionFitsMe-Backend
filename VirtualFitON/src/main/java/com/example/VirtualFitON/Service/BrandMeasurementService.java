package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Repositories.BrandMeasurementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandMeasurementService {
    @Autowired
    private BrandMeasurementRepository brandMeasurementRepository;

    @Autowired
    private ModelMapper modelMapper;
    public BrandMeasurementDTO saveMeasurement(BrandMeasurementDTO brandMeasurementDTO){
        brandMeasurementRepository.save(modelMapper.map(brandMeasurementDTO, BrandMeasurement.class));
        return brandMeasurementDTO;
    }
}
