package com.example.VirtualFitON.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.VirtualFitON.DTO.Filter;
import com.example.VirtualFitON.DTO.FilterDTO;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.DTO.ProductInfoDTO;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Exceptions.ProductNotFoundException;
import com.example.VirtualFitON.Exceptions.ProductSaveException;
import com.example.VirtualFitON.Models.Brand;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductColorSize;
import com.example.VirtualFitON.Repositories.BrandRepository;
import com.example.VirtualFitON.Repositories.ProductColorSizeRepository;
import com.example.VirtualFitON.Repositories.ProductImageRepository;
import com.example.VirtualFitON.Repositories.ProductRepository;
import com.example.VirtualFitON.Repositories.ReviewRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductServiceTest {
    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private ProductColorSizeRepository productColorSizeRepository;

    @MockBean
    private ProductImageRepository productImageRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @MockBean
    private ReviewRepository reviewRepository;

    /**
     * Method under test: {@link ProductService#getProductInformation(String)}
     */
    @Test
    void testGetProductInformation() {
        // Arrange
        ArrayList<ProductColorSize> productColorSizeList = new ArrayList<>();
        when(productColorSizeRepository.findByProductId(Mockito.<String>any())).thenReturn(productColorSizeList);
        when(productColorSizeRepository.findColorsByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(productColorSizeRepository.findSizeCountsByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(reviewRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        ProductInfoDTO actualProductInformation = productService.getProductInformation("42");

        // Assert
        verify(productColorSizeRepository).findByProductId(eq("42"));
        verify(productColorSizeRepository).findColorsByProductProductId(eq("42"));
        verify(productColorSizeRepository).findSizeCountsByProductId(eq("42"));
        verify(productRepository).findByProductId(eq("42"));
        verify(reviewRepository).findByProductProductId(eq("42"));
        assertEquals("42", actualProductInformation.getProductId());
        assertEquals("Product Category", actualProductInformation.getCategory());
        assertEquals("Product Name", actualProductInformation.getProductName());
        assertEquals(0, actualProductInformation.getImage().length);
        assertTrue(actualProductInformation.getImage_colors().isEmpty());
        BigDecimal expectedPrice = new BigDecimal("2.3");
        assertEquals(expectedPrice, actualProductInformation.getPrice());
        assertEquals(productColorSizeList, actualProductInformation.getColors());
        assertEquals(productColorSizeList, actualProductInformation.getReviews());
        assertEquals(productColorSizeList, actualProductInformation.getSizes());
    }

    /**
     * Method under test: {@link ProductService#getProductInformation(String)}
     */
    @Test
    void testGetProductInformation2() {
        // Arrange
        when(productColorSizeRepository.findByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(productColorSizeRepository.findColorsByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(productColorSizeRepository.findSizeCountsByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(reviewRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productService.getProductInformation("42"));
        verify(productColorSizeRepository).findByProductId(eq("42"));
        verify(productColorSizeRepository).findColorsByProductProductId(eq("42"));
        verify(productColorSizeRepository).findSizeCountsByProductId(eq("42"));
        verify(productRepository).findByProductId(eq("42"));
        verify(reviewRepository).findByProductProductId(eq("42"));
    }

    /**
     * Method under test: {@link ProductService#getProductInformation(String)}
     */
    @Test
    void testGetProductInformation3() {
        // Arrange
        when(productColorSizeRepository.findByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(productColorSizeRepository.findColorsByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(productColorSizeRepository.findSizeCountsByProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);
        when(reviewRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new RuntimeException("Items in Sizes:"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productService.getProductInformation("42"));
        verify(productColorSizeRepository).findByProductId(eq("42"));
        verify(productColorSizeRepository).findColorsByProductProductId(eq("42"));
        verify(productColorSizeRepository).findSizeCountsByProductId(eq("42"));
        verify(productRepository).findByProductId(eq("42"));
        verify(reviewRepository).findByProductProductId(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages(null, "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("", "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", null, "Price",
                "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "", "Price",
                "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                null, "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name", "",
                "Product Category", "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", null, "Gender", "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", null, "Brand Name", new ArrayList<>()));
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", "Gender", null, new ArrayList<>()));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages2() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any())).thenReturn(true);

        ArrayList<MultipartFile> imageFiles = new ArrayList<>();
        imageFiles.add(new MockMultipartFile("Invalid product data. Please provide all required fields.",
                new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", imageFiles));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages3() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        ArrayList<MultipartFile> imageFiles = new ArrayList<>();
        imageFiles.add(new MockMultipartFile("Invalid product data. Please provide all required fields.",
                new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));

        // Act and Assert
        assertThrows(ProductSaveException.class, () -> productService.saveProductWithImages("42", "Product Name", "Price",
                "Product Category", "Gender", "Brand Name", imageFiles));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages4() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any()))
                .thenThrow(new InvalidProductDataException("An error occurred"));

        ArrayList<MultipartFile> imageFiles = new ArrayList<>();
        imageFiles.add(new MockMultipartFile("Invalid product data. Please provide all required fields.",
                new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));

        // Act and Assert
        assertThrows(InvalidProductDataException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", imageFiles));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages5() throws IOException {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");
        when(brandRepository.findByBrandName(Mockito.<String>any())).thenReturn(brand);
        when(productRepository.existsById(Mockito.<String>any())).thenReturn(false);

        ArrayList<MultipartFile> imageFiles = new ArrayList<>();
        imageFiles.add(new MockMultipartFile("Invalid product data. Please provide all required fields.",
                new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));

        // Act and Assert
        assertThrows(ProductSaveException.class, () -> productService.saveProductWithImages("42", "Product Name", "Price",
                "Product Category", "Gender", "Brand Name", imageFiles));
        verify(brandRepository).findByBrandName(eq("Brand Name"));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProductWithImages(String, String, String, String, String, String, List)}
     */
    @Test
    void testSaveProductWithImages6() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any())).thenReturn(true);

        ArrayList<MultipartFile> imageFiles = new ArrayList<>();
        imageFiles.add(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));
        imageFiles.add(new MockMultipartFile("Invalid product data. Please provide all required fields.",
                new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1})));

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProductWithImages("42", "Product Name",
                "Price", "Product Category", "Gender", "Brand Name", imageFiles));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link ProductService#getProduct(String)}
     */
    @Test
    void testGetProduct() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");
        when(productRepository.findByProductId(Mockito.<String>any())).thenReturn(product);

        // Act
        Product actualProduct = productService.getProduct("42");

        // Assert
        verify(productRepository).findByProductId(eq("42"));
        assertSame(product, actualProduct);
    }

    /**
     * Method under test: {@link ProductService#getProduct(String)}
     */
    @Test
    void testGetProduct2() {
        // Arrange
        when(productRepository.findByProductId(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct("42"));
        verify(productRepository).findByProductId(eq("42"));
    }

    /**
     * Method under test: {@link ProductService#filterProducts(int, int, List)}
     */
    @Test
    void testFilterProducts() {
        // Arrange
        when(productRepository.findProductsbyPrice(anyInt(), anyInt())).thenReturn(new HashSet<>());

        // Act
        List<ProductDTO> actualFilterProductsResult = productService.filterProducts(1, 3, new ArrayList<>());

        // Assert
        verify(productRepository).findProductsbyPrice(eq(1), eq(3));
        assertTrue(actualFilterProductsResult.isEmpty());
    }

    /**
     * Method under test: {@link ProductService#filterProducts(int, int, List)}
     */
    @Test
    void testFilterProducts2() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        HashSet<Product> productSet = new HashSet<>();
        productSet.add(product);
        when(productRepository.findProductsbyPrice(anyInt(), anyInt())).thenReturn(productSet);

        // Act
        List<ProductDTO> actualFilterProductsResult = productService.filterProducts(1, 3, new ArrayList<>());

        // Assert
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productRepository).findProductsbyPrice(eq(1), eq(3));
        assertEquals(1, actualFilterProductsResult.size());
        ProductDTO getResult = actualFilterProductsResult.get(0);
        assertEquals("Gender", getResult.getProduct().getGender());
        assertTrue(getResult.getProductImages().isEmpty());
    }

    /**
     * Method under test: {@link ProductService#filterProducts(int, int, List)}
     */
    @Test
    void testFilterProducts3() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        HashSet<Product> productSet = new HashSet<>();
        productSet.add(product);
        when(productRepository.findProductsbyPrice(anyInt(), anyInt())).thenReturn(productSet);

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.filterProducts(1, 3, new ArrayList<>()));
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productRepository).findProductsbyPrice(eq(1), eq(3));
    }

    /**
     * Method under test: {@link ProductService#getHomeProducts(Filter)}
     */
    @Test
    void testGetHomeProducts() {
        // Arrange
        when(productRepository.findByProductCategory(Mockito.<String>any())).thenReturn(new HashSet<>());
        Filter filter = mock(Filter.class);
        when(filter.getCategory()).thenReturn("Category");
        when(filter.getTitle()).thenReturn("Category");

        // Act
        List<ProductDTO> actualHomeProducts = productService.getHomeProducts(filter);

        // Assert
        verify(filter).getCategory();
        verify(filter).getTitle();
        verify(productRepository).findByProductCategory(eq("Category"));
        assertTrue(actualHomeProducts.isEmpty());
    }

    /**
     * Method under test: {@link ProductService#getHomeProducts(Filter)}
     */
    @Test
    void testGetHomeProducts2() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Category");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Category");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Category");
        product.setProductId("42");
        product.setProductName("Category");

        HashSet<Product> productSet = new HashSet<>();
        productSet.add(product);
        when(productRepository.findByProductCategory(Mockito.<String>any())).thenReturn(productSet);
        Filter filter = mock(Filter.class);
        when(filter.getCategory()).thenReturn("Category");
        when(filter.getTitle()).thenReturn("Category");

        // Act
        List<ProductDTO> actualHomeProducts = productService.getHomeProducts(filter);

        // Assert
        verify(filter).getCategory();
        verify(filter).getTitle();
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productRepository).findByProductCategory(eq("Category"));
        assertEquals(1, actualHomeProducts.size());
        ProductDTO getResult = actualHomeProducts.get(0);
        assertEquals("Category", getResult.getProduct().getGender());
        assertTrue(getResult.getProductImages().isEmpty());
    }

    /**
     * Method under test: {@link ProductService#getHomeProducts(Filter)}
     */
    @Test
    void testGetHomeProducts3() {
        // Arrange
        when(productImageRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Category");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Category");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Category");
        product.setProductId("42");
        product.setProductName("Category");

        HashSet<Product> productSet = new HashSet<>();
        productSet.add(product);
        when(productRepository.findByProductCategory(Mockito.<String>any())).thenReturn(productSet);
        Filter filter = mock(Filter.class);
        when(filter.getCategory()).thenReturn("Category");
        when(filter.getTitle()).thenReturn("Category");

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.getHomeProducts(filter));
        verify(filter).getCategory();
        verify(filter).getTitle();
        verify(productImageRepository).findByProductProductId(eq("42"));
        verify(productRepository).findByProductCategory(eq("Category"));
    }

    /**
     * Method under test: {@link ProductService#filterProductsOld(FilterDTO)}
     */
    @Test
    void testFilterProductsOld() {
        // Arrange
        when(productColorSizeRepository.findFilteredProductColorSize(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Integer>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<BigDecimal>any(),
                Mockito.<BigDecimal>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ProductDTO> actualFilterProductsOldResult = productService.filterProductsOld(new FilterDTO());

        // Assert
        verify(productColorSizeRepository).findFilteredProductColorSize(isNull(), isNull(), eq(1), isNull(), isNull(),
                isNull(), isNull(), isNull());
        assertTrue(actualFilterProductsOldResult.isEmpty());
    }

    /**
     * Method under test: {@link ProductService#filterProductsOld(FilterDTO)}
     */
    @Test
    void testFilterProductsOld2() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productColorSizeRepository.findFilteredProductColorSize(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Integer>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<BigDecimal>any(),
                Mockito.<BigDecimal>any(), Mockito.<String>any())).thenReturn(productList);
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<ProductDTO> actualFilterProductsOldResult = productService.filterProductsOld(new FilterDTO());

        // Assert
        verify(productColorSizeRepository).findFilteredProductColorSize(isNull(), isNull(), eq(1), isNull(), isNull(),
                isNull(), isNull(), isNull());
        verify(productImageRepository).findByProductProductId(eq("42"));
        assertEquals(1, actualFilterProductsOldResult.size());
        ProductDTO getResult = actualFilterProductsOldResult.get(0);
        assertEquals("Gender", getResult.getProduct().getGender());
        assertTrue(getResult.getProductImages().isEmpty());
    }

    /**
     * Method under test: {@link ProductService#filterProductsOld(FilterDTO)}
     */
    @Test
    void testFilterProductsOld3() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productColorSizeRepository.findFilteredProductColorSize(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Integer>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<BigDecimal>any(),
                Mockito.<BigDecimal>any(), Mockito.<String>any())).thenReturn(productList);
        when(productImageRepository.findByProductProductId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        BigDecimal min = new BigDecimal("2.3");

        // Act
        List<ProductDTO> actualFilterProductsOldResult = productService.filterProductsOld(new FilterDTO("Categories",
                "Size", new FilterDTO.PriceRange(min, new BigDecimal("2.3")), "Color", "Gender", "Brand"));

        // Assert
        verify(productColorSizeRepository).findFilteredProductColorSize(eq("Color"), eq("Size"), eq(1), eq("Categories"),
                eq("Brand"), isA(BigDecimal.class), isA(BigDecimal.class), eq("Gender"));
        verify(productImageRepository).findByProductProductId(eq("42"));
        assertEquals(1, actualFilterProductsOldResult.size());
        ProductDTO getResult = actualFilterProductsOldResult.get(0);
        assertEquals("Gender", getResult.getProduct().getGender());
        assertTrue(getResult.getProductImages().isEmpty());
    }

    /**
     * Method under test: {@link ProductService#filterProductsOld(FilterDTO)}
     */
    @Test
    void testFilterProductsOld4() {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");

        Product product = new Product();
        product.setBrand(brand);
        product.setGender("Gender");
        product.setPrice(new BigDecimal("2.3"));
        product.setProductCategory("Product Category");
        product.setProductId("42");
        product.setProductName("Product Name");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productColorSizeRepository.findFilteredProductColorSize(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Integer>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<BigDecimal>any(),
                Mockito.<BigDecimal>any(), Mockito.<String>any())).thenReturn(productList);
        when(productImageRepository.findByProductProductId(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.filterProductsOld(new FilterDTO()));
        verify(productColorSizeRepository).findFilteredProductColorSize(isNull(), isNull(), eq(1), isNull(), isNull(),
                isNull(), isNull(), isNull());
        verify(productImageRepository).findByProductProductId(eq("42"));
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> actualAllProducts = productService.getAllProducts();

        // Assert
        verify(productRepository).findAll();
        assertTrue(actualAllProducts.isEmpty());
        assertSame(productList, actualAllProducts);
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new ProductNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> productService.getAllProducts());
        verify(productRepository).findAll();
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act and Assert
        assertThrows(ProductAlreadyExistsException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct2() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any()))
                .thenThrow(new ProductNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ProductSaveException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct3() throws IOException {
        // Arrange
        when(productRepository.existsById(Mockito.<String>any()))
                .thenThrow(new InvalidProductDataException("An error occurred"));

        // Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct4() throws IOException {
        // Arrange
        Brand brand = new Brand();
        brand.setBrandId("42");
        brand.setBrandName("Brand Name");
        when(brandRepository.findByBrandName(Mockito.<String>any())).thenReturn(brand);
        when(productRepository.existsById(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(ProductSaveException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
        verify(brandRepository).findByBrandName(eq("Brand Name"));
        verify(productRepository).existsById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct5() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct(null, "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct6() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("", "Product Name", "Price", "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct7() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", null, "Price", "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct8() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "", "Price", "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct9() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", null, "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct10() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", "", "Product Category", "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct11() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", null, "Gender", "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct12() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", null, "Brand Name"));
    }

    /**
     * Method under test:
     * {@link ProductService#saveProduct(String, String, String, String, String, String)}
     */
    @Test
    void testSaveProduct13() throws IOException {
        // Arrange, Act and Assert
        assertThrows(InvalidProductDataException.class,
                () -> productService.saveProduct("42", "Product Name", "Price", "Product Category", "Gender", null));
    }
}
