package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

@Entity
public class CustomerContact {
    @Id
    @EmbeddedId
    private CustomerContactId id;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(name = "contactNo",insertable = false, updatable = false)
    private String contactNo;

    public CustomerContactId getId() {
        return id;
    }

    public void setId(CustomerContactId id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
