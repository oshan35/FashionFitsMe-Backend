package com.example.VirtualFitON.Models;

import jakarta.persistence.Id;

public class CustomerContact {
    @Id
    private String customerId;
    @Id
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
