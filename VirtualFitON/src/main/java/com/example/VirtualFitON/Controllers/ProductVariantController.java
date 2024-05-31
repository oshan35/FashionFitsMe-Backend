package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.DTO.ProductVariantDTO;
import com.example.VirtualFitON.Service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ProductVariant")
@CrossOrigin
public class ProductVariantController {
    @Autowired
    private ProductVariantService productVariantService;

    @PostMapping("/saveProductVariant")
    public ProductVariantDTO saveProductVariant(@RequestBody ProductVariantDTO productVariantDTO)
    {
        return productVariantService.saveProductVariant(productVariantDTO);
    }
}
