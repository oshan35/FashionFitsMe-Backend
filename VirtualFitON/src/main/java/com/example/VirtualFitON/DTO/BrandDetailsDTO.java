package com.example.VirtualFitON.DTO;

import java.util.List;

public class BrandDetailsDTO {
    public BrandDetailsDTO(String brandId, String brandName, byte[] productMedia, List<BrandMeasurementDTO> measurements) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.productMedia = productMedia;
        this.measurements = measurements;
    }

    public BrandDetailsDTO() {
    }
    private String brandId;
    private String brandName;
    private byte[] productMedia;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public byte[] getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(byte[] productMedia) {
        this.productMedia = productMedia;
    }

    public List<BrandMeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<BrandMeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    private List<BrandMeasurementDTO> measurements;
}
