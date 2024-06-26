package com.example.VirtualFitON.Models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;


import java.math.BigDecimal;

@Entity
public class ProductVariant {

    public ProductVariant(Long variantId, Product product, String color, String size, BigDecimal price, Integer noOfItems, byte[] image) {
        this.variantId = variantId;
        this.product = product;
        this.color = color;
        this.size = size;
        this.price = price;
        this.noOfItems = noOfItems;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private String color;
    private String size;
    private BigDecimal price;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private Integer noOfItems;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

}
