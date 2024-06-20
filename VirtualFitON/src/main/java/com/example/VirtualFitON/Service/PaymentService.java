package com.example.VirtualFitON.Service;
import java.time.format.DateTimeFormatter;
import com.example.VirtualFitON.DTO.PaymentRequest;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {


    private final CustomerRepository customerRepository;

    private final ShoppingCartRepository shoppingCartRepository;


    private final AddressRepository addressRepository;


    private final OrderRepository orderRepository;


    private final ShipmentRepository shipmentRepository;
    @Autowired
    public PaymentService(CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository, AddressRepository addressRepository, OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public int createOrder(PaymentRequest paymentRequest) {
        Customer customer = customerRepository.findByCustomerId(paymentRequest.getCustomerId());

        Address shippingAddress = new Address( paymentRequest.getShippingDetails().getCompany(),paymentRequest.getShippingDetails().getCity(),paymentRequest.getShippingDetails().getStreet(),paymentRequest.getShippingDetails().getAddressName(),paymentRequest.getShippingDetails().getRegion(),paymentRequest.getShippingDetails().getPostalCode(),customer);
        Address savedAddress=addressRepository.save(shippingAddress);
        int cartId = customerRepository.findCartId(paymentRequest.getCustomerId());
        ShoppingCart cart = shoppingCartRepository.findByCartId(cartId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(formatter);
        Shipment shipment = new Shipment();
        shipment.setShipmentStatus("Ordered");
        shipment.setShipmentDate(formattedDate);
        shipment.setAddress(savedAddress);
        Shipment savedShipment = shipmentRepository.save(shipment);
        System.out.println("shipment details"+shipment.getShippingId());
        Order order = new Order();
        order.setCustomer(customer);
        order.setShoppingCart(cart);
        order.setTotal(paymentRequest.getTotal());
        order.setSubTotal(paymentRequest.getSubTotal());
        order.setShipping(paymentRequest.getShipping());
        order.setTaxes(paymentRequest.getTaxes());
        order.setEmail(paymentRequest.getEmail());
        order.setPhone(paymentRequest.getPhone());
        order.setShipment(savedShipment);
        order.setOrderDate(formattedDate);


        orderRepository.save(order);
        System.out.println("order  Date " + formattedDate);

        System.out.println("order  id " + order.getOrderId());
        return order.getOrderId();

    }
}