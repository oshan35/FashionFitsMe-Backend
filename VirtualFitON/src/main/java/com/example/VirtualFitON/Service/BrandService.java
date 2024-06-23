package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.Exceptions.BrandNotFoundException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

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
