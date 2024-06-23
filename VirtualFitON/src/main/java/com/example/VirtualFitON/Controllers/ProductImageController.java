package com.example.VirtualFitON.Controllers;

import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "http://34.222.253.72:3000", allowCredentials = "true")
@RestController
@RequestMapping(value = "product")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @PostMapping("/addProductImage")
    public ProductImage addProductImage(@RequestBody ProductImage productImage){
        return productImageService.saveProductImage(productImage);
    }

    @PostMapping("/addProductImages")
    public List<ProductImage> addProductImages(@RequestBody List<ProductImage> productImages){
        return productImageService.saveProductImages(productImages);
    }

    @GetMapping("/getAllProductImages")
    public List<ProductImage> getAllProductImages(){
        return productImageService.getAllProductImages();
    }

    @GetMapping("/getProductImageById/{id}")
    public ProductImage getProductImageById(@PathVariable Long id){
        return productImageService.findProductImageById(id);
    }


    @PutMapping("/updateProductImage")
    public ProductImage updateProductImage(@RequestBody ProductImage productImage){
        return productImageService.updateProductImage(productImage);
    }

    @DeleteMapping("/deleteProductImage/{id}")
    public String deleteProductImage(@PathVariable Long id){
        return productImageService.deleteProductImage(id);
    }




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
                                              @RequestParam("imageData")MultipartFile image) {
        try {
            productImageService.saveProductImage(productId,colour,image);
            return ResponseEntity.ok("Product Image saved successfully.");


        }

        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    }

