package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Review;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductInfoDTO {
    private String ProductId;

    private String ProductName;
    private BigDecimal price;

    private List<Object[]> sizes;

    private List<String> colors;

    private List<Review> reviews;

    private byte[] image;

    private String category;

    private Map<String,byte[]> image_colors;

    private String description;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(String productId, String productName, BigDecimal price, List<Object[]> sizes, List<String> colors, List<Review> reviews, byte[] image, String category, Map<String, byte[]> image_colors,String description) {
        ProductId = productId;
        ProductName = productName;
        this.price = price;
        this.sizes = sizes;
        this.colors = colors;
        this.reviews = reviews;
        this.image = image;
        this.category = category;
        this.image_colors = image_colors;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Object[]> getSizes() {
        return sizes;
    }

    public void setSizes(List<Object[]> sizes) {
        this.sizes = sizes;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public Map<String, byte[]> getImage_colors() {
        return image_colors;
    }

    public void setImage_colors(Map<String, byte[]> image_colors) {
        this.image_colors = image_colors;
    }
}
