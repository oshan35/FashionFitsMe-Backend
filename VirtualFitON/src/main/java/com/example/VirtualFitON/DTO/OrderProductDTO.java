package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Address;
import com.example.VirtualFitON.Models.Product;

import java.util.Date;

public class OrderProductDTO {
    private OrderItemDTO product;
    private Address deliveryAddress;
    private String email;
    private String phone;

    public OrderProductDTO(OrderItemDTO product, Address deliveryAddress, String email, String phone) {
        this.product = product;
        this.deliveryAddress = deliveryAddress;
        this.email = email;
        this.phone = phone;
    }

    public OrderProductDTO() {
    }

    public OrderItemDTO getProduct() {
        return product;
    }

    public void setProduct(OrderItemDTO product) {
        this.product = product;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
}
