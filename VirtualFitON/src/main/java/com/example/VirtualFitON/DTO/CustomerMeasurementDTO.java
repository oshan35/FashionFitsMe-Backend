package com.example.VirtualFitON.DTO;

public class CustomerMeasurementDTO {

    private int customerId;
    private Double ankle_circumference;
    private Double arm_length;
    private Double bicep_circumference;
    private Double calf_circumference;
    private Double chest_circumference;
    private Double forearm_circumference;
    private Double head_circumference;
    private Double hip_circumference;
    private Double inside_leg_length;
    private Double neck_circumference;
    private Double shoulder_breadth;
    private Double shoulder_to_crotch;
    private Double thigh_circumference;
    private Double waist_circumference;
    private Double wrist_circumference;

    public CustomerMeasurementDTO(int customerId, Double ankle_circumference, Double arm_length, Double bicep_circumference, Double calf_circumference, Double chest_circumference, Double forearm_circumference, Double head_circumference, Double hip_circumference, Double inside_leg_length, Double neck_circumference, Double shoulder_breadth, Double shoulder_to_crotch, Double thigh_circumference, Double waist_circumference, Double wrist_circumference) {
        this.customerId = customerId;
        this.ankle_circumference = ankle_circumference;
        this.arm_length = arm_length;
        this.bicep_circumference = bicep_circumference;
        this.calf_circumference = calf_circumference;
        this.chest_circumference = chest_circumference;
        this.forearm_circumference = forearm_circumference;
        this.head_circumference = head_circumference;
        this.hip_circumference = hip_circumference;
        this.inside_leg_length = inside_leg_length;
        this.neck_circumference = neck_circumference;
        this.shoulder_breadth = shoulder_breadth;
        this.shoulder_to_crotch = shoulder_to_crotch;
        this.thigh_circumference = thigh_circumference;
        this.waist_circumference = waist_circumference;
        this.wrist_circumference = wrist_circumference;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CustomerMeasurementDTO() {
    }

    public Double getAnkle_circumference() {
        return ankle_circumference;
    }

    public void setAnkle_circumference(Double ankle_circumference) {
        this.ankle_circumference = ankle_circumference;
    }

    public Double getArm_length() {
        return arm_length;
    }

    public void setArm_length(Double arm_length) {
        this.arm_length = arm_length;
    }

    public Double getBicep_circumference() {
        return bicep_circumference;
    }

    public void setBicep_circumference(Double bicep_circumference) {
        this.bicep_circumference = bicep_circumference;
    }

    public Double getCalf_circumference() {
        return calf_circumference;
    }

    public void setCalf_circumference(Double calf_circumference) {
        this.calf_circumference = calf_circumference;
    }

    public Double getChest_circumference() {
        return chest_circumference;
    }

    public void setChest_circumference(Double chest_circumference) {
        this.chest_circumference = chest_circumference;
    }

    public Double getForearm_circumference() {
        return forearm_circumference;
    }

    public void setForearm_circumference(Double forearm_circumference) {
        this.forearm_circumference = forearm_circumference;
    }

    public Double getHead_circumference() {
        return head_circumference;
    }

    public void setHead_circumference(Double head_circumference) {
        this.head_circumference = head_circumference;
    }

    public Double getHip_circumference() {
        return hip_circumference;
    }

    public void setHip_circumference(Double hip_circumference) {
        this.hip_circumference = hip_circumference;
    }

    public Double getInside_leg_length() {
        return inside_leg_length;
    }

    public void setInside_leg_length(Double inside_leg_length) {
        this.inside_leg_length = inside_leg_length;
    }

    public Double getNeck_circumference() {
        return neck_circumference;
    }

    public void setNeck_circumference(Double neck_circumference) {
        this.neck_circumference = neck_circumference;
    }

    public Double getShoulder_breadth() {
        return shoulder_breadth;
    }

    public void setShoulder_breadth(Double shoulder_breadth) {
        this.shoulder_breadth = shoulder_breadth;
    }

    public Double getShoulder_to_crotch() {
        return shoulder_to_crotch;
    }

    public void setShoulder_to_crotch(Double shoulder_to_crotch) {
        this.shoulder_to_crotch = shoulder_to_crotch;
    }

    public Double getThigh_circumference() {
        return thigh_circumference;
    }

    public void setThigh_circumference(Double thigh_circumference) {
        this.thigh_circumference = thigh_circumference;
    }

    public Double getWaist_circumference() {
        return waist_circumference;
    }

    public void setWaist_circumference(Double waist_circumference) {
        this.waist_circumference = waist_circumference;
    }

    public Double getWrist_circumference() {
        return wrist_circumference;
    }

    public void setWrist_circumference(Double wrist_circumference) {
        this.wrist_circumference = wrist_circumference;
    }
}
