package com.example.VirtualFitON.Models;



import jakarta.persistence.*;


@Entity
@Table(name="product_shopping_cart")
public class ProductShoppingCart {
    @Id
    @EmbeddedId
    private ProductShoppingCartId id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @MapsId("cartId")
    private ShoppingCart cart;

    public ProductShoppingCart(ProductShoppingCartId id, Product product, ShoppingCart cart) {
        this.id = id;
        this.product = product;
        this.cart = cart;
    }

    public ProductShoppingCart() {
    }

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