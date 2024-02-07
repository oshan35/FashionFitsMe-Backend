package com.example.VirtualFitON.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductShoppingCartId implements Serializable {
    private String productId;
    private String cartId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
