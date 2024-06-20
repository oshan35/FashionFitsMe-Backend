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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private String color;
    private String size;
    private BigDecimal price;
    private Integer noOfItems;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

}
