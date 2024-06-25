package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Service.ProductColorSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://54.191.229.94:3000", allowCredentials = "true")

public class ProductColorSizeController {
    @Autowired
    ProductColorSizeService productColorSizeService;


    @PutMapping("/add-product-information")
    public  ResponseEntity<String> addProductInformation(@RequestParam ("productId") String productId,
                                        @RequestParam ("color") String color,
                                        @RequestParam ("size") String size,
                                        @RequestParam ("quantity") int quantity){
        try {
            productColorSizeService.saveProductInformation(productId, color, size, quantity);
            return ResponseEntity.ok("Product color size information saved successfully.");
        } catch (InvalidColorException | InvalidSizeException | InvalidQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found for ID: " + productId);
        } catch (ProductColorSizeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product color size already exists for product ID: " + productId + ", color: " + color + ", size: " + size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}