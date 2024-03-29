package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="Product")
public class Product {

    @Id
    @Column(name = "product_id")
    private String productId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;


    public Product(String productId, Brand brand, String productName, BigDecimal price) {
        this.productId = productId;
        this.brand = brand;
        this.productName = productName;
        this.price = price;
    }

    public Product() {
    }

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
