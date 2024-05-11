package com.example.VirtualFitON.DTO;

import java.math.BigDecimal;

public class CartItemDTO {
    private String productId;

    private String productName;

    private BigDecimal price;

    private String color;

    private String size;

    private boolean inStock;

    private byte[] image;

    public CartItemDTO(String productId, String productName, BigDecimal price, String color, String size, boolean inStock, byte[] image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.color = color;
        this.size = size;
        this.inStock = inStock;
        this.image = image;
    }

    public CartItemDTO() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
