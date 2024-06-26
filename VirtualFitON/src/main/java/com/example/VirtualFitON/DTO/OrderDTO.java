package com.example.VirtualFitON.DTO;

import com.example.VirtualFitON.Models.Address;
import com.example.VirtualFitON.Models.Product;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private int orderId;
    private List<OrderProductDTO> products;
    private String orderDate;
    private Address deliveryAddress;
    private String email;
    private String phone;
    private double total;

    private double subTotal;
    private double shipping;
    private double taxes;

    private String selectedDeliveryMethod;

    public OrderDTO() {
    }

    public OrderDTO(int orderId,List<OrderProductDTO> products, String orderDate, Address deliveryAddress, String email, String phone, double total, double subTotal, double shipping, double taxes, String selectedDeliveryMethod) {
        this.products = products;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.email = email;
        this.phone = phone;
        this.total = total;
        this.subTotal = subTotal;
        this.shipping = shipping;
        this.taxes = taxes;
        this.selectedDeliveryMethod = selectedDeliveryMethod;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getSelectedDeliveryMethod() {
        return selectedDeliveryMethod;
    }

    public void setSelectedDeliveryMethod(String selectedDeliveryMethod) {
        this.selectedDeliveryMethod = selectedDeliveryMethod;
    }
}
