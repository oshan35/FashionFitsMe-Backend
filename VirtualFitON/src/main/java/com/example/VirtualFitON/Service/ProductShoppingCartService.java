package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
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

    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;



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
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
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
            e.printStackTrace();
            throw e;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public List<ProductColorSize> getProductColorSizeByCartId(String cartId) {
        return productShoppingCartRepository.findProductColorSizeByCartId(cartId);
    }




}