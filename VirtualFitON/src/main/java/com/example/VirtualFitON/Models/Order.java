package com.example.VirtualFitON.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderId")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", foreignKey = @ForeignKey(name = "FK_orders"))
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", foreignKey = @ForeignKey(name = "FK_orders_shoppingCart"))
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "shippingId", referencedColumnName = "shippingId", foreignKey = @ForeignKey(name = "FK_orders_shipments"))
    private Shipment shipment;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "total")
    private Integer total;

}
