//package com.example.VirtualFitON.Models;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name="Product")
//public class Product {
//
//    @Id
//    @Column(name = "product_id")
//    private String productId;
//
//    @ManyToOne
//    @JoinColumn(name = "brand_id")
//    private Brand brand;
//
//    @Column(name = "product_name")
//    private String productName;
//
//    @Column(name = "price")
//    private BigDecimal price;
//
//
//    public Product(String productId, Brand brand, String productName, BigDecimal price) {
//        this.productId = productId;
//        this.brand = brand;
//        this.productName = productName;
//        this.price = price;
//    }
//
//    public Product() {
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//}

package com.example.VirtualFitON.Models;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity


public class Product {

    @Id
    private String productId;
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;


    public Product(String productId, Brand brand, String productName, BigDecimal price,String productCategory,String gender,String description) {
        this.productId = productId;
        this.brand = brand;
        this.productName = productName;
        this.price = price;
        this.productCategory=productCategory;
        this.gender=gender;
        this.description=description;
    }

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getProductCategory() {
        return productCategory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


}
