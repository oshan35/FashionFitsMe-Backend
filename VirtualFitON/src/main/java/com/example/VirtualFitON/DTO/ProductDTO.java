package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductImage;

import java.util.List;

public class ProductDTO {
    private Product product;
    private List<ProductImage>productImages;

    public ProductDTO(Product product, List<ProductImage> productImages) {
        this.product = product;
        this.productImages = productImages;
    }

    public ProductDTO() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
