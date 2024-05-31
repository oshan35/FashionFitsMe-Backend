package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Service.BrandMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "BrandMeasurement")
public class BrandMeasurementController {

    @Autowired
    private BrandMeasurementService brandMeasurementService;

    @PostMapping("/saveMeasurement")
    public BrandMeasurementDTO saveMeasurement(@RequestBody BrandMeasurementDTO brandMeasurementDTO){
        return brandMeasurementService.saveMeasurement(brandMeasurementDTO);
    }
}
