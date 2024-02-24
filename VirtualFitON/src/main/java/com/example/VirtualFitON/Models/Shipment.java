package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id

    @Column(name = "shipping_id")
    private String shippingId;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "shipment_status")
    private String shipmentStatus;

    @Column(name = "shipment_date")
    private Date shipmentDate;
}
