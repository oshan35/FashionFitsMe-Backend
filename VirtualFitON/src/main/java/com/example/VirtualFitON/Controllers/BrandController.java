package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.BrandDTO;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "Brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/saveBrand")
    public BrandDTO saveBrand(@RequestBody BrandDTO brandDTO)
    {
        return brandService.saveBrand(brandDTO);
    }

    @GetMapping("/getBrand")
    public List<BrandDTO> getBrand()
    {
        return brandService.getAllBrands();
    }





}
