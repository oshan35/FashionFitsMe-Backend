package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.CartItemDTO;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductImageRepository productImageRepository;



    public List<CartItemDTO> getCartProductList(List<ProductShoppingCart> cartItems){
        List<CartItemDTO> cartItemDetails = new ArrayList<>();
        for(ProductShoppingCart cartItem:cartItems){
            byte[] productImage = productImageRepository.findByProductIdAndColor(cartItem.getProduct().getProductId(), cartItem.getProductColor());
            CartItemDTO itemDetail = new CartItemDTO(cartItem.getProduct().getProductId(), cartItem.getProduct().getProductName(), cartItem.getProduct().getPrice(), cartItem.getProductColor(), cartItem.getProductSize(),true, productImage);
            cartItemDetails.add(itemDetail);

        }
        return cartItemDetails;
    }
}
