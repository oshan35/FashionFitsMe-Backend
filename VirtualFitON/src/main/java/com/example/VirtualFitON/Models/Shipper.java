package com.example.VirtualFitON.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shipper {

    @Id
    private String shipperId;

    private String shipperName;
    private String contactNo;
}
