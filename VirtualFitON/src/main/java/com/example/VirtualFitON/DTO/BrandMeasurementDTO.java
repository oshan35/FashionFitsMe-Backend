package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandMeasurementDTO {

    private Long measurementId;

    private String category;
    private String size;
    private String item;
    private Brand brand;

    private BigDecimal ankleCircumference;
    private BigDecimal armLength;
    private BigDecimal bicepCircumference;
    private BigDecimal calfCircumference;
    private BigDecimal chestCircumference;
    private BigDecimal forearmCircumference;
    private BigDecimal headCircumference;
    private BigDecimal hipCircumference;
    private BigDecimal insideLegLength;
    private BigDecimal neckCircumference;
    private BigDecimal shoulderBreadth;
    private BigDecimal shoulderToCrotch;
    private BigDecimal thighCircumference;
    private BigDecimal waistCircumference;
}
