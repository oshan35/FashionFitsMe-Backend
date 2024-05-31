package com.example.VirtualFitON.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private String productId;
    private String productName;
    private BrandDTO brand;
    private BigDecimal price;
    private String description;
    private byte[] productMedia;
    private String productCategory;
    private String itemCategory;

}
