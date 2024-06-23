package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDto {
        private String productId;
        private String color;
        private byte[] imageData;

        public ImageDto(ProductImage productImage) {
            this.productId = productImage.getProduct().getProductId();
            this.color = productImage.getColour();
            this.imageData = productImage.getImageData();
        }
    }
}
