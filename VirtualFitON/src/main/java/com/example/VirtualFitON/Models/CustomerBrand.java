package com.example.VirtualFitON.Models;

import jakarta.persistence.*;
@Entity
public class CustomerBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private CustomerBrandId id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("brandId")
    @JoinColumn(name = "brandId")
    private Brand brand;

    public CustomerBrandId getId() {
        return id;
    }

    public void setId(CustomerBrandId id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
