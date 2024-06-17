package com.example.VirtualFitON.DTO;

public class BodyDataDTO {
    private int customerId;


    private String gender;


    private float weight;

    private float height;

    public BodyDataDTO(int customerId,String gender, float weight, float height) {
        this.customerId = customerId;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
