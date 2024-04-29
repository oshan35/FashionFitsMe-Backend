package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Address;

public class PaymentRequest {

    private String email;
    private String phone;
    private Address shippingDetails;
    private String selectedDeliveryMethod;

    private int customerId;

    private double total;

    private double subTotal;
    private double shipping;
    private double taxes;


    public PaymentRequest(String email, String phone, Address shippingDetails, String selectedDeliveryMethod, int customerId, double total, double subTotal, double shipping, double taxes) {
        this.email = email;
        this.phone = phone;
        this.shippingDetails = shippingDetails;
        this.selectedDeliveryMethod = selectedDeliveryMethod;
        this.customerId = customerId;
        this.total = total;
        this.subTotal = subTotal;
        this.shipping = shipping;
        this.taxes = taxes;
    }

    public PaymentRequest() {
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
