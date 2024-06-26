package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private Shipment shipment;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "total")
    private double total;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "taxes")
    private double taxes;

    @Column(name = "shipping")
    private double shipping;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name="order_status")
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




    public Order(String orderStatus, Integer orderId, Customer customer, ShoppingCart shoppingCart, Shipment shipment, String orderDate, double total, double subTotal, double taxes, double shipping, String email, String phone) {
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

    public Order() {
    }

    public double getTotal() {
        return total;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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


}
