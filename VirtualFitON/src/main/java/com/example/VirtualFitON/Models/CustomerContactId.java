package com.example.VirtualFitON.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable

public class CustomerContactId implements Serializable {
   @Column(name="customer_id")
    private String customer_Id;
    @Column(name="contact_no")
    private String contact_No;

    public String getCustomerId() {
        return customer_Id;
    }

    public void setCustomerId(String customerId) {
        this.customer_Id = customerId;
    }

    public String getContactNo() {
        return contact_No;
    }

    public void setContactNo(String contactNo) {
        this.contact_No = contactNo;
    }
}
