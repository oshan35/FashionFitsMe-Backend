package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.BrandMeasurementDTO;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.BrandMeasurement;
import com.example.VirtualFitON.Service.BrandMeasurementService;
import com.example.VirtualFitON.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "BrandMeasurement")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
public class BrandMeasurementController {
    @Autowired
    private BrandMeasurementService brandMeasurementService;

    @PutMapping("/{measurementId}")
    public ResponseEntity<BrandMeasurement> updateBrandMeasurement(
            @PathVariable("measurementId") Long measurementId,
            @RequestBody BrandMeasurement updatedMeasurement) {

        BrandMeasurement updated = brandMeasurementService.updateBrandMeasurement(measurementId, updatedMeasurement);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @GetMapping("/{brandId}-{category}-{item}-{size}")
    public List<BrandMeasurement> getMeasurements(
            @PathVariable("brandId") String brandId,
            @PathVariable("category") String category,
            @PathVariable("item") String item,
            @PathVariable("size") String size) {
        return brandMeasurementService.getMeasurements(brandId, category, item, size);
    }

    @GetMapping("/category-item-size")
    public ResponseEntity<BrandMeasurement> getBrandMeasurementByCategoryItemSize(
            @RequestParam String category,
            @RequestParam String item,
            @RequestParam String size) {

        BrandMeasurement measurement = brandMeasurementService.findBrandMeasurementByCategoryItemSize(category, item, size);
        return ResponseEntity.ok(measurement);
    }
    @PostMapping("/addBrandMeasurement")
    public BrandMeasurement addBrandMeasurements(@RequestBody BrandMeasurement brandMeasurement)
    {
        return brandMeasurementService.saveBrandMeasurement(brandMeasurement);
    }

    @PostMapping("/addBrandMeasurements")
    public List<BrandMeasurement> addBrandMeasurements(@RequestBody List<BrandMeasurement> measurements){
        return brandMeasurementService.saveBrandMeasurements(measurements);
    }

    @GetMapping("/getAllBrandMeasurements")
    public List<BrandMeasurement> getAllBrandMeasurements(){
        return brandMeasurementService.getAllBrandMeasurement();
    }

    @GetMapping("/getBrandMeasurement/{id}")
    public BrandMeasurement findBrandMeasurementById(@PathVariable Long id){
        return brandMeasurementService.findBrandMeasurementById(id);
    }



    @DeleteMapping("/deleteBrandMeasurement/{id}")
    public String deleteBrandMeasurement(@PathVariable Long id){
        return brandMeasurementService.deleteBrandMeasurement(id);
    }

}
