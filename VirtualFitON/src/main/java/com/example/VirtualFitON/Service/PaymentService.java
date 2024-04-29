package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.Address;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.AddressRepository;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.OrderRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderRepository orderRepository;


    public void createOrder(PaymentRequest paymentRequest) {

        Address shippingAddress = paymentRequest.getShippingDetails();
        Customer customer = customerRepository.findByCustomerId(paymentRequest.getCustomerId());
        int cartId = customerRepository.findCartId(paymentRequest.getCustomerId());
        ShoppingCart cart = shoppingCartRepository.findByCartId(cartId);

        Order order = new Order();
        order.setCustomer(customer);
        order.setShoppingCart(cart);
        order.setTotal(paymentRequest.getTotal());
        order.setSubTotal(paymentRequest.getSubTotal());
        order.setShipping(paymentRequest.getShipping());
        order.setTaxes(paymentRequest.getTaxes());
        order.setEmail(paymentRequest.getEmail());
        order.setPhone(paymentRequest.getPhone());
        orderRepository.save(order);
        System.out.println("order  id " + order.getOrderId());


    }
}