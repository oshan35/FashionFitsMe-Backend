package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.DTO.AddProductToCartRequest;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Exceptions.DatabaseAccessException;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Exceptions.ProductImageSaveException;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.ProductService;
import com.example.VirtualFitON.Service.ProductShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductShoppingCartController {
    @Autowired
    ProductShoppingCartService productShoppingCartService;
    @Autowired
    ProductShoppingCartRepository productShoppingCartRepository;
    
    @GetMapping("/product_shopping_cart/{cartId}")
    public ResponseEntity<?> getProductListByCartId(@PathVariable int cartId) {
        try {
            List<ProductDTO> productList = productShoppingCartService.getProductListByCartId(cartId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @PostMapping("/product_shopping_cart/addProducts")
    public ResponseEntity<String> addProductToCart(@RequestBody AddProductToCartRequest request) {
        try {
            System.out.println(request.getProductId());
            System.out.println(request.getCustomerId());
            System.out.println("selected colour"+request.getSelectedColor());
            System.out.println("selected size" + request.getSelectedSize());
            productShoppingCartService.addProductToCart(request.getProductId(), request.getCustomerId(),request.getSelectedColor(),request.getSelectedSize());

            return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
        } catch (InvalidProductDataException e) {
            return new ResponseEntity<>("Invalid product data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DatabaseAccessException e) {
            return new ResponseEntity<>("Database access error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ProductAlreadyExistsException e) {
            return new ResponseEntity<>("Product already exists in the cart: " + e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/product_shopping_cart/totals/{cartId}")
    public ResponseEntity<Object> getTotals(@PathVariable("cartId") int cartId) {
        try {
            BigDecimal totalAmount = productShoppingCartService.getTotalAmount(cartId);
            BigDecimal discountAmount = productShoppingCartService.getDiscountAmount(cartId);

            if (totalAmount == null || discountAmount == null) {
                return new ResponseEntity<>("Unable to retrieve totals", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Map<String, BigDecimal> response = new HashMap<>();
            response.put("totalAmount", totalAmount);
            response.put("discountAmount", discountAmount);
            response.put("estimatedTotal", totalAmount.subtract(discountAmount));

            return ResponseEntity.ok().body(response);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Database error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product_shopping_cart/{cartId}/product-color-sizes")
    public ResponseEntity<List<ProductColorSize>> getProductColorSizeByCartId(@PathVariable int cartId) {
        List<ProductColorSize> productColorSizeList = productShoppingCartService.getProductColorSizeByCartId(cartId);
        return ResponseEntity.ok().body(productColorSizeList);
    }



}
