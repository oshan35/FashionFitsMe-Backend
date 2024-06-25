package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.io.Serializable;
@Embeddable
public class CustomerBrandId implements Serializable {
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "brand_id")
    private String brandId;

    public CustomerBrandId(String customerId, String brandId) {
        this.customerId = customerId;
        this.brandId = brandId;
    }

    public CustomerBrandId() {

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
