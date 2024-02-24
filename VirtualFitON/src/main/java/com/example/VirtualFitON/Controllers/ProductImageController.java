package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/product-images/getImage/{Id}")

    public ResponseEntity<?> getProductImagesByProductId(@PathVariable String Id) {
        try {
            List<ProductImage> productImages = productImageService.getImageListByProductId(Id);
            return ResponseEntity.ok(productImages);


        }

            catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

        }

    }

