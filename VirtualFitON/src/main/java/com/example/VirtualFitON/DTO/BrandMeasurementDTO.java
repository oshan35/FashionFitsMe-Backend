package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Brand;


import java.math.BigDecimal;


public class BrandMeasurementDTO {

    private Long measurementId;
    private double ankleCircumference;
    private double armLength;
    private double bicepCircumference;
    private double calfCircumference;

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
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

    private String category;
    private double chestCircumference;
    private double forearmCircumference;
    private double headCircumference;
    private double hipCircumference;
    private double insideLegLength;
    private String item;
    private double neckCircumference;
    private double shoulderBreadth;
    private double shoulderToCrotch;
    private String size;
    private double thighCircumference;
    private double waistCircumference;
    private double wristCircumference;
    public BrandMeasurementDTO() {
    }

    public BrandMeasurementDTO(Long measurementId, double ankleCircumference, double armLength, double bicepCircumference, double calfCircumference, String category, double chestCircumference, double forearmCircumference, double headCircumference, double hipCircumference, double insideLegLength, String item, double neckCircumference, double shoulderBreadth, double shoulderToCrotch, String size, double thighCircumference, double waistCircumference, double wristCircumference) {
        this.measurementId = measurementId;
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

}
