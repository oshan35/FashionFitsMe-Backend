package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    public void saveProductWithImages(String productId, String productName, String price, List<MultipartFile> imageFiles) throws IOException {
        try {
            // Check if product data is valid
            if (productId == null || productId.isEmpty() || productName == null || productName.isEmpty() || price == null || price.isEmpty() || imageFiles == null || imageFiles.isEmpty()) {
                throw new InvalidProductDataException("Invalid product data. Please provide all required fields.");
            }

            // Check if the product already exists
            if (productRepository.existsById(productId)) {
                throw new ProductAlreadyExistsException("Product with ID " + productId + " already exists.");
            }

            // Save the product
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setPrice(new BigDecimal(price));
            productRepository.save(product);

            // Save the images
            for (MultipartFile imageFile : imageFiles) {
                byte[] imageData = imageFile.getBytes();
                ProductImage productImage = new ProductImage();
                productImage.setProduct(product);
                productImage.setImageData(imageData);
                productImageRepository.save(productImage);
            }
        } catch (IOException e) {
            throw new ProductImageSaveException("Failed to save product image: " + e.getMessage());
        } catch (InvalidProductDataException | ProductAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductSaveException("Failed to save product: " + e.getMessage());
        }
    }

    public Product getProduct(String productId) {
        return productRepository.findByProductId(productId);
    }
}