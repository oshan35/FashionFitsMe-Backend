package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.ProductVariantDTO;
import com.example.VirtualFitON.Models.ProductVariant;
import com.example.VirtualFitON.Repositories.BrandRepository;
import com.example.VirtualFitON.Repositories.ProductVariantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ModelMapper modelMapper;
    public ProductVariantDTO saveProductVariant(ProductVariantDTO productVariantDTO){
        productVariantRepository.save(modelMapper.map(productVariantDTO, ProductVariant.class));
        return productVariantDTO;
    }
}
