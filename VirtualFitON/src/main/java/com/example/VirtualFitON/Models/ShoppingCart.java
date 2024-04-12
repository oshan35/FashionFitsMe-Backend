
package com.example.VirtualFitON.Models;

import com.example.VirtualFitON.Models.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
    @Id
    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "purchese_status")
    private boolean purchaseStatus;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    public ShoppingCart(String cartId, BigDecimal totalAmount, Customer customer, boolean purchaseStatus, BigDecimal discountAmount) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.purchaseStatus = purchaseStatus;
        this.discountAmount = discountAmount;
    }

    public ShoppingCart() {
    }


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