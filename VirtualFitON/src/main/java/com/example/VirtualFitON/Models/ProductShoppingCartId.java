package com.example.VirtualFitON.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductShoppingCartId implements Serializable {
    @Column(name="product_id")
    private String productId;
    @Column(name="cart_id")
    private int cartId;

    public ProductShoppingCartId(String productId, int cartId) {
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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


}
