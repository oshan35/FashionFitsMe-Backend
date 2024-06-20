package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductImage;

import java.util.List;

public class OrderItemDTO {

    private Product product;
    private byte[] image;

    public OrderItemDTO(Product product, byte[] image) {
        this.product = product;
        this.image = image;
    }

    public OrderItemDTO() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
