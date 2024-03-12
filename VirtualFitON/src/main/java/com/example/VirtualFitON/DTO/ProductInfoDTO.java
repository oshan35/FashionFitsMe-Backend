package com.example.VirtualFitON.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductInfoDTO {
    private String ProductId;

    private String ProductName;
    private BigDecimal price;

    private List<String> sizes;

    private List<String> colors;

    private byte[] image;

    private Map<String,byte[]> image_colors;

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(String productId, String productName, BigDecimal price, List<String> sizes, List<String> colors, Map<String, byte[]> image_colors) {
        ProductId = productId;
        ProductName = productName;
        this.price = price;
        this.sizes = sizes;
        this.colors = colors;
        this.image_colors = image_colors;
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

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
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
