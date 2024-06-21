package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.DTO.*;
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
import java.util.Set;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://34.222.253.72:3000", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/add-product-with-images")
    public ResponseEntity<String> saveProductWithImages(
            @RequestParam("productId") String productId,
            @RequestParam("productName") String productName,
            @RequestParam("price") String price,
            @RequestParam("productCategory") String productCategory,
            @RequestParam("gender") String gender,
            @RequestParam("brand") String brand,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles
    ) {
        try {
            System.out.println("Test: "+productId);
            productService.saveProductWithImages(productId, productName, price, productCategory,gender,brand,imageFiles);
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

    @PostMapping("/add-product")
    public ResponseEntity<String> saveProduct(
            @RequestParam("productId") String productId,
            @RequestParam("productName") String productName,
            @RequestParam("price") String price,
            @RequestParam("productCategory") String productCategory,
            @RequestParam("gender") String gender,
            @RequestParam("brand") String brand
    ) {
        try {
            System.out.println("Test: "+productId);
            productService.saveProduct(productId, productName, price, productCategory,gender,brand);
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

    @GetMapping("/{Id}")
    public ResponseEntity<?> getProduct(@PathVariable String Id) {
        try {
            System.out.println(Id);
            Product p = productService.getProduct(Id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/filter")
    public List<ProductDTO> filterProductsOld(@RequestBody FilterDTO filterDTO) {
        System.out.println(filterDTO.getCategories());
        System.out.println(filterDTO.getColor());
        System.out.println(filterDTO.getPrice());
        System.out.println(filterDTO.getBrand());
        System.out.println(filterDTO.getSize());
        System.out.println(filterDTO.getGender());


        return productService.filterProductsOld(filterDTO);
    }

    @PostMapping("/filter-products")
    public ResponseEntity<?> filterProducts(@RequestBody FilterRequestDTO filterRequest) {
        System.out.println("Minimum Price: " + filterRequest.getMinPrice());
        System.out.println("Maximum Price: " + filterRequest.getMaxPrice());
        System.out.println("Selected Filters:");
       List<ProductDTO> filteredProducts = productService.filterProducts(filterRequest.getMinPrice(), filterRequest.getMaxPrice(), filterRequest.getSelectedFilters());
        System.out.println(filteredProducts.size());

        for (Filter selectedFilters : filterRequest.getSelectedFilters()) {
            System.out.println("Title: " + selectedFilters.getTitle());
            System.out.println("Category: " + selectedFilters.getCategory());
        }        return ResponseEntity.ok(filteredProducts);
    }



    @GetMapping("/getProductInformation")
    public ResponseEntity<?> getAllProducts(@RequestParam("productId") String productId) {


        System.out.println(productId);
             ProductInfoDTO productInfoDTO=productService.getProductInformation(productId);
            return new ResponseEntity<>(productInfoDTO, HttpStatus.OK);


    }

    @PostMapping("/getHomeProducts")
    public ResponseEntity<List<ProductDTO>> filterProducts(@RequestBody Filter filter) {
        System.out.println(filter.getCategory());
        System.out.println(filter.getTitle());

        List<ProductDTO> filteredProducts = productService.getHomeProducts(filter);

        if (filteredProducts != null) {
            return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
