package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private int shippingId;


    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "shipment_status")
    private String shipmentStatus;

    @Column(name = "shipment_date")
    private String shipmentDate;

    public Shipment(int shippingId, Address address, String shipmentStatus, String shipmentDate) {
        this.shippingId = shippingId;
        this.address = address;
        this.shipmentStatus = shipmentStatus;
        this.shipmentDate = shipmentDate;
    }

    public Shipment() {
    }

    public Shipment(Address shippingDetails, String ordered, String formattedDate) {
        this.address = shippingDetails;
        this.shipmentStatus = shipmentStatus;
        this.shipmentDate = formattedDate;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
}
