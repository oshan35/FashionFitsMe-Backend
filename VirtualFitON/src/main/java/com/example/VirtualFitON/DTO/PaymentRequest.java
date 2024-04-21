package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Address;

public class PaymentRequest {

    private String email;
    private String phone;
    private Address shippingDetails;
    private String selectedDeliveryMethod;

    private int customerId;


    public PaymentRequest(String email, String phone, Address shippingDetails, String selectedDeliveryMethod,int customerId) {
        this.email = email;
        this.phone = phone;
        this.shippingDetails = shippingDetails;
        this.selectedDeliveryMethod = selectedDeliveryMethod;
        this.customerId=customerId;
    }

    public PaymentRequest() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(Address shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getSelectedDeliveryMethod() {
        return selectedDeliveryMethod;
    }

    public void setSelectedDeliveryMethod(String selectedDeliveryMethod) {
        this.selectedDeliveryMethod = selectedDeliveryMethod;
    }
}
