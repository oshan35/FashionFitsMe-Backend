package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

@Entity
@Table(name="customer_contact")
public class CustomerContact {
    @Id
    @EmbeddedId
    private CustomerContactId id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @MapsId("customer_Id")
    private Customer customer;


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
        return id.getContactNo();
    }

    public void setContactNo(String contactNo) {
        id.setContactNo(contactNo);
    }
}
