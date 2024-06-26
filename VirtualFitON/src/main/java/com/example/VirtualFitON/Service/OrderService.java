package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.OrderNotFoundException;
import com.example.VirtualFitON.Models.Order;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.OrderRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductShoppingCartService productShoppingCartService;

    private final ProductImageRepository productImageRepository;


    private final ProductShoppingCartRepository productShoppingCartRepository;

    public Order updateOrderStatus(Integer orderId, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(newStatus);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderDetailsDTO getOrderDetails(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Convert Order entity to OrderDetailsDTO
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            orderDetailsDTO.setOrderId(order.getOrderId());
            orderDetailsDTO.setCustomer(order.getCustomer());
            orderDetailsDTO.setShoppingCart(order.getShoppingCart());
            orderDetailsDTO.setShipment(order.getShipment());
            orderDetailsDTO.setOrderDate(order.getOrderDate());
            orderDetailsDTO.setTotal(order.getTotal());
            orderDetailsDTO.setSubTotal(order.getSubTotal());
            orderDetailsDTO.setTaxes(order.getTaxes());
            orderDetailsDTO.setShipping(order.getShipping());
            orderDetailsDTO.setEmail(order.getEmail());
            orderDetailsDTO.setPhone(order.getPhone());
            orderDetailsDTO.setOrderStatus(order.getOrderStatus());
            return orderDetailsDTO;
        } else {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
    }

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductShoppingCartService productShoppingCartService, ProductImageRepository productImageRepository, ProductShoppingCartRepository productShoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.productShoppingCartService = productShoppingCartService;
        this.productImageRepository = productImageRepository;
        this.productShoppingCartRepository = productShoppingCartRepository;
    }

    public OrderDTO getOrderDetails(int orderId){

        Order order=orderRepository.findByOrderId(orderId);
        List<OrderProductDTO>orderProducts=new ArrayList<>();

        ShoppingCart shoppingCart=order.getShoppingCart();
        List<ProductShoppingCart> cartProductList;
        cartProductList = productShoppingCartRepository.findCartProductsByCartId(shoppingCart.getCartId());
        List<Product> products=productShoppingCartService.getCartProductListByCartId(shoppingCart.getCartId());
        for ( ProductShoppingCart cartProduct : cartProductList){
            byte[] productImage = productImageRepository.findByProductIdAndColor(cartProduct.getProduct().getProductId(), cartProduct.getProductColor());
            OrderItemDTO productDTO=new OrderItemDTO(cartProduct.getProduct(),productImage);
            OrderProductDTO orderProductDTO=new OrderProductDTO(productDTO,order.getShipment().getAddress(),  order.getEmail(), order.getPhone());
            orderProducts.add(orderProductDTO);
        }
        OrderDTO orderDTO=new OrderDTO(order.getOrderId(),orderProducts,order.getOrderDate(),order.getShipment().getAddress(), order.getEmail(), order.getPhone(), order.getTotal(),order.getSubTotal(),order.getShipping(),order.getTaxes(),order.getShipment().getShipmentStatus());
        System.out.println("order products size"+orderProducts.size());
        return orderDTO;

    }


    public List<OrderDTO> getOrdersByCustomerId(int customerId) {
        List<Order> orders = orderRepository.findByCustomer_CustomerId(customerId);
        List<OrderDTO> orderDTOList = new ArrayList<>();



        for (Order order : orders) {

            System.out.println("printed order id in order summary"+order.getOrderId());
            List<OrderProductDTO> orderProducts = new ArrayList<>();

            ShoppingCart shoppingCart = order.getShoppingCart();
            List<ProductShoppingCart> cartProductList = productShoppingCartRepository.findCartProductsByCartId(shoppingCart.getCartId());

            for (ProductShoppingCart cartProduct : cartProductList) {
                byte[] productImage = productImageRepository.findByProductIdAndColor(cartProduct.getProduct().getProductId(), cartProduct.getProductColor());
                OrderItemDTO productDTO = new OrderItemDTO(cartProduct.getProduct(), productImage);
                OrderProductDTO orderProductDTO = new OrderProductDTO(productDTO, order.getShipment().getAddress(), order.getEmail(), order.getPhone());
                orderProducts.add(orderProductDTO);
            }

            OrderDTO orderDTO = new OrderDTO(
                    order.getOrderId(),
                    orderProducts,
                    order.getOrderDate(),
                    order.getShipment().getAddress(),
                    order.getEmail(),
                    order.getPhone(),
                    order.getTotal(),
                    order.getSubTotal(),
                    order.getShipping(),
                    order.getTaxes(),
                    order.getShipment().getShipmentStatus()
            );
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }


}
