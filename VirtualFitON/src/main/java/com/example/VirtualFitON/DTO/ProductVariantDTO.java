package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVariantDTO {

    private String variantId;
    private Product product;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer noOfItems;
    private byte[] image;
}

