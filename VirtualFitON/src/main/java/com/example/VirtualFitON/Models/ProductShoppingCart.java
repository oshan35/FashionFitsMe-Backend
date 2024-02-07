package com.example.VirtualFitON.Models;



import jakarta.persistence.*;


@Entity
public class ProductShoppingCart {
    @EmbeddedId
    private ProductShoppingCartId id;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId", insertable = false, updatable = false)
    private ShoppingCart cart;

    public ProductShoppingCartId getId() {
        return id;
    }

    public void setId(ProductShoppingCartId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}