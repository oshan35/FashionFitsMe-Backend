package com.example.VirtualFitON.Models;

import jakarta.persistence.*;
@Entity
@Table(name="customer_brand")
public class CustomerBrand {
    @Id
    @EmbeddedId
    private CustomerBrandId id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="brand_id")
    @MapsId( "brandId")
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
