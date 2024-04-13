package com.example.VirtualFitON.DTO;

public class AddProductToCartRequest {
    private String productId;
    private int customerId;

    public AddProductToCartRequest(String productId, int customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public AddProductToCartRequest() {
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
