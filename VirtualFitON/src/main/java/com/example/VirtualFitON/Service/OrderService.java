package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.OrderDTO;
import com.example.VirtualFitON.DTO.OrderItemDTO;
import com.example.VirtualFitON.DTO.OrderProductDTO;
import com.example.VirtualFitON.DTO.ProductDTO;
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

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductShoppingCartService productShoppingCartService;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductShoppingCartRepository productShoppingCartRepository;
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
        OrderDTO orderDTO=new OrderDTO(orderProducts,order.getOrderDate(),order.getShipment().getAddress(), order.getEmail(), order.getPhone(), order.getTotal(),order.getSubTotal(),order.getShipping(),order.getTaxes(),order.getShipment().getShipmentStatus());
        return orderDTO;

    }

}
