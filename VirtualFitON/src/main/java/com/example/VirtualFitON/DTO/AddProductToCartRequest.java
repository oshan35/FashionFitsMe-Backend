package com.example.VirtualFitON.DTO;

public class AddProductToCartRequest {
    private String productId;
    private int customerId;

    private String selectedColor;

    private String selectedSize;

    public AddProductToCartRequest(String productId, int customerId, String selectedColor, String selectedSize) {
        this.productId = productId;
        this.customerId = customerId;
        this.selectedColor = selectedColor;
        this.selectedSize = selectedSize;
    }

    public AddProductToCartRequest() {
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
