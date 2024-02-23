package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Models.ProductShoppingCartId;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductShoppingCartService{
    @Autowired
    private ProductShoppingCartRepository productShoppingCartRepository;

    public List<Product> getProductListByCartId(String cartId) {
//        ProductShoppingCartId id = new ProductShoppingCartId();
//        id.setCartId(cartId);
//        id.setProductId(productId);

        List<Product> products = productShoppingCartRepository.findProductsByCartId(cartId);
        System.out.println(products.size());
        return products;
    }



}