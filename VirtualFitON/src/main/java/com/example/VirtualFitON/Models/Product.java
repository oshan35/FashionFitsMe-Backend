package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @Column(name = "productId")
    private String productId;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
