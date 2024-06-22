package com.example.VirtualFitON.Models;


import jakarta.persistence.*;

@Entity
@Table(name="brand_measurement")
public class BrandMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurement_id;


    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;



    @Column(name = "ankle_circumference")
    private double ankleCircumference;

    @Column(name = "arm_length")
    private double armLength;

    @Column(name = "bicep_circumference")
    private double bicepCircumference;

    @Column(name = "calf_circumference")
    private double calfCircumference;

    @Column(name = "category")
    private String category;

    @Column(name = "chest_circumference")
    private double chestCircumference;

    @Column(name = "forearm_circumference")
    private double forearmCircumference;

    @Column(name = "head_circumference")
    private double headCircumference;

    @Column(name = "hip_circumference")
    private double hipCircumference;

    @Column(name = "inside_leg_length")
    private double insideLegLength;

    @Column(name = "item")
    private String item;

    @Column(name = "neck_circumference")
    private double neckCircumference;

    @Column(name = "shoulder_breadth")
    private double shoulderBreadth;

    @Column(name = "shoulder_to_crotch")
    private double shoulderToCrotch;

    @Column(name = "size")
    private String size;

    @Column(name = "thigh_circumferenc")
    private double thighCircumference;

    @Column(name = "waist_circumference")
    private double waistCircumference;

    @Column(name = "wrist_circumference")
    private double wristCircumference;

    public BrandMeasurement(Long measurement_id, Brand brand, double ankleCircumference,
                            double armLength, double bicepCircumference,
                            double calfCircumference, String category, double chestCircumference,
                            double forearmCircumference, double headCircumference,
                            double hipCircumference, double insideLegLength, String item,
                            double neckCircumference, double shoulderBreadth, double shoulderToCrotch,
                            String size, double thighCircumference, double waistCircumference,
                            double wristCircumference) {
        this.measurement_id = measurement_id;
        this.brand = brand;
        this.ankleCircumference = ankleCircumference;
        this.armLength = armLength;
        this.bicepCircumference = bicepCircumference;
        this.calfCircumference = calfCircumference;
        this.category = category;
        this.chestCircumference = chestCircumference;
        this.forearmCircumference = forearmCircumference;
        this.headCircumference = headCircumference;
        this.hipCircumference = hipCircumference;
        this.insideLegLength = insideLegLength;
        this.item = item;
        this.neckCircumference = neckCircumference;
        this.shoulderBreadth = shoulderBreadth;
        this.shoulderToCrotch = shoulderToCrotch;
        this.size = size;
        this.thighCircumference = thighCircumference;
        this.waistCircumference = waistCircumference;
        this.wristCircumference = wristCircumference;
    }


    public BrandMeasurement() {
    }

    public Long getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(Long measurement_id) {
        this.measurement_id = measurement_id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getAnkleCircumference() {
        return ankleCircumference;
    }

    public void setAnkleCircumference(double ankleCircumference) {
        this.ankleCircumference = ankleCircumference;
    }

    public double getArmLength() {
        return armLength;
    }

    public void setArmLength(double armLength) {
        this.armLength = armLength;
    }

    public double getBicepCircumference() {
        return bicepCircumference;
    }

    public void setBicepCircumference(double bicepCircumference) {
        this.bicepCircumference = bicepCircumference;
    }

    public double getCalfCircumference() {
        return calfCircumference;
    }

    public void setCalfCircumference(double calfCircumference) {
        this.calfCircumference = calfCircumference;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(double chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public double getForearmCircumference() {
        return forearmCircumference;
    }

    public void setForearmCircumference(double forearmCircumference) {
        this.forearmCircumference = forearmCircumference;
    }

    public double getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(double headCircumference) {
        this.headCircumference = headCircumference;
    }

    public double getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(double hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public double getInsideLegLength() {
        return insideLegLength;
    }

    public void setInsideLegLength(double insideLegLength) {
        this.insideLegLength = insideLegLength;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getNeckCircumference() {
        return neckCircumference;
    }

    public void setNeckCircumference(double neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    public double getShoulderBreadth() {
        return shoulderBreadth;
    }

    public void setShoulderBreadth(double shoulderBreadth) {
        this.shoulderBreadth = shoulderBreadth;
    }

    public double getShoulderToCrotch() {
        return shoulderToCrotch;
    }

    public void setShoulderToCrotch(double shoulderToCrotch) {
        this.shoulderToCrotch = shoulderToCrotch;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getThighCircumference() {
        return thighCircumference;
    }

    public void setThighCircumference(double thighCircumference) {
        this.thighCircumference = thighCircumference;
    }

    public double getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public double getWristCircumference() {
        return wristCircumference;
    }

    public void setWristCircumference(double wristCircumference) {
        this.wristCircumference = wristCircumference;
    }
}
