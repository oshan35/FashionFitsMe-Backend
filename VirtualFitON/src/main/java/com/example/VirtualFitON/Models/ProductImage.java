package com.example.VirtualFitON.Models;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "colour")
    private String colour;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    public ProductImage(Long id, Product product, byte[] imageData,String colour) {
        this.id = id;
        this.product = product;
        this.imageData = imageData;
        this.colour=colour;
    }

    public ProductImage() {
    }

    public ProductImage(Product product, String colour, byte[] imageData) {
        this.product = product;
        this.colour = colour;
        this.imageData = imageData;
    }



    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
