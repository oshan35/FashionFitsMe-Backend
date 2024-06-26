package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.DTO.BrandDetailsDTO;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "Brand")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin(origins = "http://3.87.155.15:3000", allowCredentials = "true")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("/add-brand")
    public Brand addBrand(@RequestBody Brand brand){
        return brandService.saveBrand(brand);
    }

    @PostMapping("/add-brands")
    public List<Brand> addBrands(@RequestBody List<Brand> brands){
        return brandService.saveBrands(brands);
    }

    @GetMapping("/getAllBrands")
    public List<Brand> getAllBrands(){
        return brandService.getAllBrands();
    }

    @GetMapping("/getBrandById/{id}")
    public Brand getBrandById(@PathVariable String id){
        return brandService.findBrandById(id);
    }

    @GetMapping("/getBrand/{brandName}")
    public Brand getBrandByName(@PathVariable String brandName){
        return brandService.findBrandByName(brandName);
    }

    @PutMapping("/updateBrand")
    public Brand updateBrand(@RequestBody Brand brand){
        return brandService.updateBrand(brand);
    }

    @DeleteMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable String id){
        return brandService.deleteBrand(id);
    }

    @GetMapping("/{brandId}")
    public BrandDetailsDTO getBrandDetails(@PathVariable String brandId) {
        return brandService.getBrandDetails(brandId);
    }



}
