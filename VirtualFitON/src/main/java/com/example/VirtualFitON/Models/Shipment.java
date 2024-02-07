package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id

    @Column(name = "shippingId")
    private String shippingId;

    @ManyToOne
    @JoinColumn(name = "shipperId", referencedColumnName = "shipperId", foreignKey = @ForeignKey(name = "FK_shipments_shipper"))
    private Shipper shipper;

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId", foreignKey = @ForeignKey(name = "FK_shipments"))
    private Address address;

    @Column(name = "shipmentStatus")
    private String shipmentStatus;

    @Column(name = "shipmentDate")
    private Date shipmentDate;
}
