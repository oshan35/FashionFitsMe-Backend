package com.example.VirtualFitON.DTO;



public class BrandDTO {


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


    public BrandDTO(String brandId, String brandName, byte[] productMedia) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.productMedia = productMedia;
    }

    public byte[] getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(byte[] productMedia) {
        this.productMedia = productMedia;
    }



}
