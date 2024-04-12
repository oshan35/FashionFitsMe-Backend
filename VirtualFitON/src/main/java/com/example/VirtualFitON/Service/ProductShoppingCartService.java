package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private  ProductImageRepository productImageRepository;



    public List<ProductDTO> getProductListByCartId(String cartId) {
        List<Product> products = productShoppingCartRepository.findProductsByCartId(cartId);

        return mapToProductDTO(products);

    }

    private List<ProductDTO> mapToProductDTO(List<Product> filteredProducts) {
        List<ProductDTO> filteredProductDTOs = new ArrayList<>();

        for (Product product : filteredProducts) {

            if (product != null) {

                List<ProductImage> productImages = productImageRepository.findByProductProductId(product.getProductId());
                if (productImages != null) { // Check if productImages is not null
                    filteredProductDTOs.add(new ProductDTO(product, productImages));
                } else {
                    // Handle the case when productImages is null
                    // You can log an error message or take appropriate action
                    System.out.println("Product images for product ID " + product.getProductId() + " are null.");
                }
            } else {
                // Handle the case when product is null
                // You can log an error message or take appropriate action
                System.out.println("Encountered a null product.");
            }
        }
        return filteredProductDTOs;
    }


    public BigDecimal getTotalAmount(int cartId) {

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

    public BigDecimal getDiscountAmount(int cartId) {
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