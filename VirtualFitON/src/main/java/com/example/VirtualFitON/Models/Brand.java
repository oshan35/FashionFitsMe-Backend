package com.example.VirtualFitON.Models;
import jakarta.persistence.*;


@Entity
public class Brand {

    @Id
    @Column(name = "brandId")
    private String brandId;

    @Column(name = "brandName")
    private String brandName;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}



