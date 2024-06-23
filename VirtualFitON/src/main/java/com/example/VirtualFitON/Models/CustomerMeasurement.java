package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

@Entity
@Table(name="customer_measurement")
public class CustomerMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;



    @Column(name = "ankle_circumference")
    private Double ankleCircumference;

    @Column(name = "arm_length")
    private Double armLength;

    @Column(name = "bicep_circumference")
    private Double bicepCircumference;

    @Column(name = "calf_circumference")
    private Double calfCircumference;

    @Column(name = "chest_circumference")
    private Double chestCircumference;

    @Column(name = "forearm_circumference")
    private Double forearmCircumference;

    @Column(name = "head_circumference")
    private Double headCircumference;

    @Column(name = "hip_circumference")
    private Double hipCircumference;

    @Column(name = "inside_leg_length")
    private Double insideLegLength;

    @Column(name = "neck_circumference")
    private Double neckCircumference;

    @Column(name = "shoulder_breadth")
    private Double shoulderBreadth;

    @Column(name = "shoulder_to_crotch")
    private Double shoulderToCrotch;

    @Column(name = "thigh_circumference")
    private Double thighCircumference;

    @Column(name = "waist_circumference")
    private Double waistCircumference;

    @Column(name = "wrist_circumference")
    private Double wristCircumference;

    @Lob
    @Column(name = "body_model")
    private byte[] bodyModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getAnkleCircumference() {
        return ankleCircumference;
    }

    public void setAnkleCircumference(Double ankleCircumference) {
        this.ankleCircumference = ankleCircumference;
    }

    public Double getArmLength() {
        return armLength;
    }

    public void setArmLength(Double armLength) {
        this.armLength = armLength;
    }

    public Double getBicepCircumference() {
        return bicepCircumference;
    }

    public void setBicepCircumference(Double bicepCircumference) {
        this.bicepCircumference = bicepCircumference;
    }

    public Double getCalfCircumference() {
        return calfCircumference;
    }

    public void setCalfCircumference(Double calfCircumference) {
        this.calfCircumference = calfCircumference;
    }

    public Double getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(Double chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public Double getForearmCircumference() {
        return forearmCircumference;
    }

    public void setForearmCircumference(Double forearmCircumference) {
        this.forearmCircumference = forearmCircumference;
    }

    public Double getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(Double headCircumference) {
        this.headCircumference = headCircumference;
    }

    public Double getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(Double hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public Double getInsideLegLength() {
        return insideLegLength;
    }

    public void setInsideLegLength(Double insideLegLength) {
        this.insideLegLength = insideLegLength;
    }

    public Double getNeckCircumference() {
        return neckCircumference;
    }

    public void setNeckCircumference(Double neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    public Double getShoulderBreadth() {
        return shoulderBreadth;
    }

    public void setShoulderBreadth(Double shoulderBreadth) {
        this.shoulderBreadth = shoulderBreadth;
    }

    public Double getShoulderToCrotch() {
        return shoulderToCrotch;
    }

    public void setShoulderToCrotch(Double shoulderToCrotch) {
        this.shoulderToCrotch = shoulderToCrotch;
    }

    public Double getThighCircumference() {
        return thighCircumference;
    }

    public void setThighCircumference(Double thighCircumference) {
        this.thighCircumference = thighCircumference;
    }

    public Double getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(Double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public Double getWristCircumference() {
        return wristCircumference;
    }

    public void setWristCircumference(Double wristCircumference) {
        this.wristCircumference = wristCircumference;
    }

    public byte[] getBodyModel() {
        return bodyModel;
    }

    public void setBodyModel(byte[] bodyModel) {
        this.bodyModel = bodyModel;
    }
}
