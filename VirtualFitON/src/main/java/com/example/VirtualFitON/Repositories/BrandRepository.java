package com.example.VirtualFitON.Repositories;

import com.example.VirtualFitON.Models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository <Brand, String> {

}
