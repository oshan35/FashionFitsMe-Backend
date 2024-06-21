package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/product-images/getImage/{Id}")

    public ResponseEntity<?> getProductImagesByProductId(@PathVariable String Id) {
        try {
            List<byte[]> productImages = productImageService.getImageDataListByProductId(Id);
            return ResponseEntity.ok(productImages);


        }

            catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

        }


    @PostMapping("/product-images/addImage")

    public ResponseEntity<?> addProductImages(@RequestParam("productId") String productId,
                                              @RequestParam("colour") String colour,
                                              @RequestParam("image")MultipartFile image) {
        try {
            productImageService.saveProductImage(productId,colour,image);
            return ResponseEntity.ok("Product Image saved successfully.");


        }

        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    }

