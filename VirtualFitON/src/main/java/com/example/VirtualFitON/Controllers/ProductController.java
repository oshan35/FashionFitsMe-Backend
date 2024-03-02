package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.DTO.FilterDTO;
import com.example.VirtualFitON.Exceptions.DatabaseAccessException;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Exceptions.ProductImageSaveException;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/products/add-product")
    public ResponseEntity<String> saveProductWithImages(
            @RequestParam("productId") String productId,
            @RequestParam("productName") String productName,
            @RequestParam("price") String price,
            @RequestParam("productCategory") String productCategory,
            @RequestParam("gender") String gender,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles
    ) {
        try {
            System.out.println("Test: "+productId);
            productService.saveProductWithImages(productId, productName, price, productCategory,gender,imageFiles);
            return ResponseEntity.ok("Product saved successfully.");
        } catch (InvalidProductDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ProductImageSaveException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (DatabaseAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process image file: " + e.getMessage());
        }
    }

    @GetMapping("/products/{Id}")
    public ResponseEntity<?> getProduct(@PathVariable String Id) {
        try {
            System.out.println(Id);
            Product p = productService.getProduct(Id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products/filter")
    public List<Product> filterProducts(@RequestBody FilterDTO filterDTO) {
        System.out.println(filterDTO.getCategories());
        System.out.println(filterDTO.getColor());
        System.out.println(filterDTO.getPrice());
        System.out.println(filterDTO.getBrand());
        System.out.println(filterDTO.getSize());
        System.out.println(filterDTO.getGender());


        return productService.filterProducts(filterDTO);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
