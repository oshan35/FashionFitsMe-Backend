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
    private String brandName;
    private String description;
    private byte[] productMedia;
    private String category;
    private String item;

}
