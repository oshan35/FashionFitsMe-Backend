package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductColorSizeId;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ProductColorSizeService {

    private final ProductColorSizeRepository productColorSizeRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ProductColorSizeService(ProductColorSizeRepository productColorSizeRepository, ProductRepository productRepository) {
        this.productColorSizeRepository = productColorSizeRepository;
        this.productRepository = productRepository;
    }

    public void saveProductInformation(String productId, String color, String size, int quantity) {
        try {
            validateColor(color);

            validateSize(size);

            validateQuantity(quantity);

            Product product = productRepository.findByProductId(productId);

            if (product == null) {
                throw new ProductNotFoundException("Product not found for ID: " + productId);
            }

            ProductColorSize existing = productColorSizeRepository.findByProductIdAndColorAndSize(productId, color, size);
            if (existing != null) {
                throw new ProductColorSizeAlreadyExistsException("Product color size already exists for product ID: " + productId + ", color: " + color + ", size: " + size);
            }
            ProductColorSizeId id = new ProductColorSizeId(productId, color, size);
            ProductColorSize productColorSize = new ProductColorSize(id,product,quantity);

            productColorSizeRepository.save(productColorSize);
        } catch (InvalidColorException | InvalidSizeException | InvalidQuantityException e) {

            System.err.println("Validation error: " + e.getMessage());
        } catch (ProductNotFoundException | ProductColorSizeAlreadyExistsException e) {

            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {

            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    private void validateColor(String color) throws InvalidColorException {
        if (color == null || color.isEmpty()) {
            throw new InvalidColorException("Color cannot be empty.");
        }

    }

    private void validateSize(String size) throws InvalidSizeException {
        if (size == null || size.isEmpty()) {
            throw new InvalidSizeException("Size cannot be empty.");
        }


        Pattern sizePattern = Pattern.compile("[XXS|XS|S|M|L|XL|XXL]+");
        if (!sizePattern.matcher(size).matches()) {
            throw new InvalidSizeException("Invalid size: " + size);
        }


    }

    private void validateQuantity(int quantity) throws InvalidQuantityException{
//        if (quantity == Integer.parseInt(null)) {
//            throw new InvalidQuantityException("Quantity cannot be empty.");
//        }


        Integer quantityInteger = quantity;
        if (!(quantityInteger instanceof Integer)) {
            throw new IllegalArgumentException("Quantity must be an Integer object.");
        }



    }

}
