
package com.example.VirtualFitON.Models;

import com.example.VirtualFitON.Models.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class ShoppingCart {
    @Id
    @Column(name = "cartId")
    private String cartId;

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(name = "purchaseStatus")
    private boolean purchaseStatus;

    @Column(name = "discountAmount")
    private BigDecimal discountAmount;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(boolean purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}