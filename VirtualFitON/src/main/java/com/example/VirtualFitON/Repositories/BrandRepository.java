package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository <Brand, String> {
}
