
package com.example.VirtualFitON.Models;

import com.example.VirtualFitON.Models.Customer;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="shopping_cart")
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "purchese_status")
    private boolean purchaseStatus;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    public ShoppingCart(int cartId, BigDecimal totalAmount,  boolean purchaseStatus, BigDecimal discountAmount) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
//        this.customer = customer;
        this.purchaseStatus = purchaseStatus;
        this.discountAmount = discountAmount;
    }

    public ShoppingCart() {
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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