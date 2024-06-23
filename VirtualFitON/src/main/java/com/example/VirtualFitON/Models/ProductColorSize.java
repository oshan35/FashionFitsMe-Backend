package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

@Entity
@Table(name="product_color_size")

public class ProductColorSize {
    @Id
    @EmbeddedId
    private ProductColorSizeId id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public ProductColorSize(ProductColorSizeId id, Product product,  int quantity) {
        this.id = id;
        this.product = product;

        this.quantity = quantity;
    }

    public ProductColorSize() {

    }

    public ProductColorSizeId getId() {
        return id;
    }

    public void setId(ProductColorSizeId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return id.getColor();
    }

    public void setColor(String color) {
        id.setColor(color);
    }

    public String getSize() {
        return id.getSize();
    }

    public void setSize(String size) {
        id.setSize(size);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
