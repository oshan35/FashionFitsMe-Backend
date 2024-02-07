package com.example.VirtualFitON.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class CustomerContactId implements Serializable {
    private String customerId;
    private String contactNo;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
