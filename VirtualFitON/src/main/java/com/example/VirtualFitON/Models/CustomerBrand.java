package com.example.VirtualFitON.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerBrand {
    @Id
    private Customer customer;

    @Id
    private Brand brandId;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }
}
