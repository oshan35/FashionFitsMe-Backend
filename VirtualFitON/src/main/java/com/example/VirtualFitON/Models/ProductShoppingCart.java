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

    @Column(name = "product_color")
    private String productColor;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "product_quantity")
    private int orderQuantity;


    public ProductShoppingCart(ProductShoppingCartId id, Product product, ShoppingCart cart) {
        this.id = id;
        this.product = product;
        this.cart = cart;
    }

    public ProductShoppingCart(ProductShoppingCartId id, Product product, ShoppingCart cart, String productColor, String productSize, int orderQuantity) {
        this.id = id;
        this.product = product;
        this.cart = cart;
        this.productColor = productColor;
        this.productSize = productSize;
        this.orderQuantity = orderQuantity;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
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