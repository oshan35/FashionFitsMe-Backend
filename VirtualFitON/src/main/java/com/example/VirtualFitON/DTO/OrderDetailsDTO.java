package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Shipment;
import com.example.VirtualFitON.Models.ShoppingCart;

public class OrderDetailsDTO {
    private Integer orderId;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private Shipment shipment;
    private String orderDate;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private double total;
    private double subTotal;
    private double taxes;



    private double shipping;
    private String email;
    private String phone;
    private String orderStatus;

    public OrderDetailsDTO(){

    }

    public OrderDetailsDTO(Integer orderId, Customer customer, ShoppingCart shoppingCart, Shipment shipment, String orderDate, double total, double subTotal, double taxes, double shipping, String email, String phone, String orderStatus) {
        this.orderId = orderId;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.shipment = shipment;
        this.orderDate = orderDate;
        this.total = total;
        this.subTotal = subTotal;
        this.taxes = taxes;
        this.shipping = shipping;
        this.email = email;
        this.phone = phone;
        this.orderStatus = orderStatus;
    }
}
