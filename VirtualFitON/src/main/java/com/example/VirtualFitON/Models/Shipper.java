package com.example.VirtualFitON.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shipper {

    @Id
    @Column(name = "shipper_id")
    private String shipperId;
    @Column(name = "shipper_Name")
    private String shipperName;
    @Column(name = "contact_no")
    private String contactNo;
}
