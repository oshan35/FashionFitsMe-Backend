package com.example.VirtualFitON.Models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import java.io.Serializable;

@Embeddable
public class ProductColorSizeId implements Serializable {
    @Column(name="product_id")
    private String productId;

    @Column(name="color")
    private String color;

    @Column(name="size")
    private String size;

    public ProductColorSizeId(String product_Id, String color, String size) {
        this.productId = product_Id;
        this.color = color;
        this.size = size;
    }

    public ProductColorSizeId() {
    }

    public String getProduct_Id() {
        return productId;
    }

    public void setProduct_Id(String product_Id) {
        this.productId = product_Id;
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
}
