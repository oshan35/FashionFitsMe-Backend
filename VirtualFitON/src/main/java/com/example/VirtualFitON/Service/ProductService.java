package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.FilterDTO;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Models.ProductImage;
import com.example.VirtualFitON.Repositories.BrandRepository;
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
    BrandRepository brandRepository;

    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;

    private Brand getBrand(String brandName) {
        try {
            Brand brand = brandRepository.findByBrandName(brandName);
            if (brand == null) {
                throw new BrandNotFoundException("Brand not found for name: " + brandName);
            }
            return brand;
        } catch (DataAccessException e) {

            e.printStackTrace();
            return null;
        }
    }

    public void saveProductWithImages(String productId, String productName, String price,String productCategory,String gender, String brandName,List<MultipartFile> imageFiles) throws IOException {
        try {
            // Check if product data is valid
            if (productId == null || productId.isEmpty() || productName == null || productName.isEmpty() || price == null || gender == null ||brandName == null|| productCategory==null || price.isEmpty() || imageFiles == null || imageFiles.isEmpty()) {
                throw new InvalidProductDataException("Invalid product data. Please provide all required fields.");
            }

            // Check if the product already exists
            if (productRepository.existsById(productId)) {
                throw new ProductAlreadyExistsException("Product with ID " + productId + " already exists.");
            }


            Brand brand=getBrand(brandName);

            // Save the product
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setPrice(new BigDecimal(price));
            product.setProductCategory(productCategory);
            product.setGender(gender);
            product.setBrand(brand);
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


    public List<ProductDTO> filterProducts(FilterDTO filterDTO) {
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

        List<ProductDTO> filteredProductDTOs = new ArrayList<>();
        System.out.println(filteredProductColorSize.size());
        for (ProductColorSize pcs : filteredProductColorSize) {

            Product product = pcs.getProduct();
            List<ProductImage> productImages = productImageRepository.findByProductProductId(product.getProductId());
            filteredProductDTOs.add(new ProductDTO(product,productImages));

        }


        return filteredProductDTOs;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}