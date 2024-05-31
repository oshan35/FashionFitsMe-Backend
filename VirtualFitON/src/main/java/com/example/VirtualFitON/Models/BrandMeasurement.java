//package com.example.VirtualFitON.Models;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="brand_measurement")
//public class BrandMeasurement {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @ManyToOne
//    @JoinColumn(name = "brand_id")
//    private Brand brand;
//
//    @Column(name = "Measurement_type")
//    private String measurementType;
//
//    @Column(name = "Measurement")
//    private String measurement;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
//
//    public String getMeasurementType() {
//        return measurementType;
//    }
//
//    public void setMeasurementType(String measurementType) {
//        this.measurementType = measurementType;
//    }
//
//    public String getMeasurement() {
//        return measurement;
//    }
//
//    public void setMeasurement(String measurement) {
//        this.measurement = measurement;
//    }
//}

package com.example.VirtualFitON.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandMeasurement {

    @Id
    private String measurementId;

    private String category;
    private String size;

    @ManyToOne
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
