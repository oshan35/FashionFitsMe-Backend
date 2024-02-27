package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductShoppingCartService{
    @Autowired
    private ProductShoppingCartRepository productShoppingCartRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;



    public List<Product> getProductListByCartId(String cartId) {
        List<Product> products = productShoppingCartRepository.findProductsByCartId(cartId);
        System.out.println(products.size());
        return products;
    }

    public BigDecimal getTotalAmount(String cartId) {
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findByCartId(cartId);
            if (shoppingCart != null) {
                return shoppingCart.getTotalAmount();
            } else {
                throw new EmptyResultDataAccessException("Shopping cart not found with ID: " + cartId, 1);
            }
        } catch (DataAccessException e) {

            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getDiscountAmount(String cartId) {
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findByCartId(cartId);
            if (shoppingCart != null) {
                return shoppingCart.getDiscountAmount();
            } else {
                throw new EmptyResultDataAccessException("Shopping cart not found with ID: " + cartId, 1);
            }
        } catch (EmptyResultDataAccessException e) {
            // Handle if the shopping cart is not found
            // Log the error or return a meaningful response
            e.printStackTrace();
        } catch (DataAccessException e) {
            // Handle other data access exceptions
            // Log the error or return a meaningful response
            e.printStackTrace();
        }
        return null;
    }




}