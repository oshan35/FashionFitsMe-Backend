package com.example.VirtualFitON.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductShoppingCartId implements Serializable {
    @Column(name="productId")
    private String productId;
    @Column(name="cartId")
    private String cartId;

    public ProductShoppingCartId(String productId, String cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public ProductShoppingCartId() {
    }

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
