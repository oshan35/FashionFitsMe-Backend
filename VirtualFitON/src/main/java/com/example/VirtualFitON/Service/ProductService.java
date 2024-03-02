package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.FilterDTO;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;

    public void saveProductWithImages(String productId, String productName, String price,String productCategory,String gender, List<MultipartFile> imageFiles) throws IOException {
        try {
            // Check if product data is valid
            if (productId == null || productId.isEmpty() || productName == null || productName.isEmpty() || price == null || gender == null || productCategory==null || price.isEmpty() || imageFiles == null || imageFiles.isEmpty()) {
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
            product.setProductCategory(productCategory);
            product.setGender(gender);
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


    public List<Product> filterProducts(FilterDTO filterDTO) {
        // Implement filtering logic based on color, size, and quantity
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        if (filterDTO.getPrice() != null) {
            minPrice = filterDTO.getPrice().getMin();
            maxPrice = filterDTO.getPrice().getMax();
        }
        FilterDTO.PriceRange priceRange = filterDTO.getPrice();
        List<ProductColorSize> filteredProductColorSize = productColorSizeRepository.findFilteredProductColorSize(
                filterDTO.getColor(),
                filterDTO.getSize(),
                1,
                filterDTO.getCategories(),
                filterDTO.getBrand(),
                minPrice,
                maxPrice,
                filterDTO.getGender()
        );

        // Extract products from filteredProductColorSize
        List<Product> filteredProducts = new ArrayList<>();
        for (ProductColorSize pcs : filteredProductColorSize) {
            filteredProducts.add(pcs.getProduct());
        }

        return filteredProducts;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}