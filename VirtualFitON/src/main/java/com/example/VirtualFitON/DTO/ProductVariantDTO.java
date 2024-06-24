package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Product;


import java.math.BigDecimal;


public class ProductVariantDTO {


    private Product product;
    private String color;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private String size;
    private BigDecimal price;
    private Integer noOfItems;
    private byte[] image;

    public ProductVariantDTO(Product product, String color, String size, BigDecimal price, Integer noOfItems, byte[] image) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.price = price;
        this.noOfItems = noOfItems;
        this.image = image;
    }
}

