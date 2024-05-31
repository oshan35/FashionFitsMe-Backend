package com.example.VirtualFitON.Models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVariant {

    @Id
    private String variantId;
    @ManyToOne
    private Product product;

    private String color;
    private String size;
    private BigDecimal price;
    private Integer noOfItems;
    @Lob
    private byte[] image;

}
