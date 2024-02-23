package com.example.VirtualFitON.Models;

import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="reviews")
public class Review {
    @Id
    @Column(name = "reviewId")
    private String reviewId;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "review")
    private String review;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
