package com.example.VirtualFitON.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class CustomerBrandId implements Serializable {
    private String customerId;
    private String brandId;
}
