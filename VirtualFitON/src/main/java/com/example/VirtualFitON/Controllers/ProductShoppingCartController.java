package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.Exceptions.DatabaseAccessException;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Exceptions.ProductImageSaveException;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.ProductService;
import com.example.VirtualFitON.Service.ProductShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductShoppingCartController {
    @Autowired
    ProductShoppingCartService productShoppingCartService;
    @Autowired
    ProductShoppingCartRepository productShoppingCartRepository;
    
    @GetMapping("/product_shopping_cart/{cartId}")
    public ResponseEntity<?> getProductListByCartId(@PathVariable String cartId) {
        try {
            List<Product> productList = productShoppingCartService.getProductListByCartId(cartId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
