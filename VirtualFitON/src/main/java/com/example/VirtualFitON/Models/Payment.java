package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @Column(name = "paymentNo")
    private String paymentNo;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", foreignKey = @ForeignKey(name = "FK_order_payment"))
    private Order order;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "paymentDetails")
    private String paymentDetails;

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
