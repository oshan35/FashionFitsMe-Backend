package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.BrandDTO;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Repositories.BrandRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    public BrandDTO saveBrand(BrandDTO brandDTO){
        brandRepository.save(modelMapper.map(brandDTO, Brand.class));
        return brandDTO;
    }
}
