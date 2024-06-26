package com.example.VirtualFitON.Models;


import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "company")
    private String company;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "region")
    private String region;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;

    public Address(int addressId, String company, String city, String street, String addressName, String region, String postalCode, Customer customer) {
        this.addressId = addressId;
        this.company = company;
        this.city = city;
        this.street = street;
        this.addressName = addressName;
        this.region = region;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    public Address() {
    }

    public Address(String company, String city, String street, String addressName, String region, String postalCode, Customer customer) {
        this.company = company;
        this.city = city;
        this.street = street;
        this.addressName = addressName;
        this.region = region;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
