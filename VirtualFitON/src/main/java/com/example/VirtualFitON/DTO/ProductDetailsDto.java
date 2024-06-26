package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class ProductDetailsDto {

    private String productId;
    private String productName;
    private BigDecimal price;
    private String productCategory;
    private String gender;
    private String brandName;
    private String description;
    private List<ColorSizeDto> colorSizes;
    private List<ImageDto> images;

    public ProductDetailsDto(Product product, List<ProductColorSize> productColorSizes, List<ProductImage> productImages) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productCategory = product.getProductCategory();
        this.gender = product.getGender();
        this.description = product.getDescription();
        this.brandName = product.getBrand().getBrandName();
        this.colorSizes = productColorSizes.stream().map(ColorSizeDto::new).collect(Collectors.toList());
        this.images = productImages.stream().map(ImageDto::new).collect(Collectors.toList());
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ColorSizeDto> getColorSizes() {
        return colorSizes;
    }

    public void setColorSizes(List<ColorSizeDto> colorSizes) {
        this.colorSizes = colorSizes;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }





    public static class ColorSizeDto {
        private String productId;
        private String color;
        private String size;
        private int quantity;

        public ColorSizeDto(ProductColorSize productColorSize) {
            this.productId = productColorSize.getProduct().getProductId();
            this.color = productColorSize.getColor();
            this.size = productColorSize.getSize();
            this.quantity = productColorSize.getQuantity();
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }


    }


    public static class ImageDto {
        private String productId;
        private String color;
        private byte[] imageData;

        public ImageDto(ProductImage productImage) {
            this.productId = productImage.getProduct().getProductId();
            this.color = productImage.getColour();
            this.imageData = productImage.getImageData();
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public byte[] getImageData() {
            return imageData;
        }

        public void setImageData(byte[] imageData) {
            this.imageData = imageData;
        }

    }
}
