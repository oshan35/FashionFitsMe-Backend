//package com.example.VirtualFitON.Service;
//import com.example.VirtualFitON.Exceptions.*;
//import com.example.VirtualFitON.Models.Product;
//import com.example.VirtualFitON.Models.ProductImage;
//import com.example.VirtualFitON.Repositories.ProductImageRepository;
//import com.example.VirtualFitON.Repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.math.BigDecimal;
//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    ProductImageRepository productImageRepository;
//
//    public void saveProductWithImage(String productId, String productName, String price, MultipartFile imageFile) throws IOException {
//        try {
//            // Check if product data is valid
//            if (productId == null || productId.isEmpty() || productName == null || productName.isEmpty() || price == null || price.isEmpty() || imageFile == null) {
//                throw new InvalidProductDataException("Invalid product data. Please provide all required fields.");
//            }
//
//            // Check if the product already exists
//            if (productRepository.existsById(productId)) {
//                throw new ProductAlreadyExistsException("Product with ID " + productId + " already exists.");
//            }
//
//
//            Product product = new Product();
//            product.setProductId(productId);
//            product.setProductName(productName);
//            product.setPrice(new BigDecimal(price));
//
//            productRepository.save(product);
//            byte[] imageData = imageFile.getBytes();
//
//            ProductImage productImage = new ProductImage();
//            productImage.setProduct(product);
//            productImage.setImageData(imageData);
//            productImageRepository.save(productImage);
//
//        }
//
//            catch (IOException e) {
//                throw new ProductImageSaveException("Failed to save product image: " + e.getMessage());
//            } catch (InvalidProductDataException e) {
//                throw e; // Re-throwing the custom exception without wrapping
//            } catch (Exception e) {
//                throw new ProductSaveException("Failed to save product: " + e.getMessage());
//            }
//
//
//    }
//    public Product getProduct(String productId){
//        Product product=productRepository.findByProductId(productId);
//        return product;
//
//    }
//
//}

package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.*;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final BrandRepository brandRepository;

    private final ProductColorSizeRepository productColorSizeRepository;


    public ProductDetailsDto getProductDetails(String productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null) {
            System.out.println("Product not found!");
        }

        List<ProductColorSize> productColorSizes = productColorSizeRepository.findByProductId(productId);
        List<ProductImage> productImages = productImageRepository.findByProductProductId(productId);

        return new ProductDetailsDto(product, productColorSizes, productImages);
    }

    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product findProductById(String id)
    {
        if(productRepository.findById(id).isEmpty())
            throw new BrandNotFoundException("Requested product doesn't exist!");
        return productRepository.findById(id).get();
    }

    public Product findProductByName(String name){
        return productRepository.findByProductName(name);
    }

    public String deleteProduct(String id){
        productRepository.deleteById(id);
        return "Product delete Successful!!" +id;
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getProductId()).orElse(null);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setProductCategory(product.getProductCategory());
        existingProduct.setGender(product.getGender());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }


    @Autowired
    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository, BrandRepository brandRepository, ProductColorSizeRepository productColorSizeRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
        this.brandRepository = brandRepository;
        this.productColorSizeRepository = productColorSizeRepository;
    }




    public ProductInfoDTO getProductInformation(String productId) {
        try {
         Product product = productRepository.findByProductId(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found for id: " + productId);
            }
            List<ProductColorSize>pcs=productColorSizeRepository.findByProductId(productId);
//            List<String> sizes=productColorSizeRepository.findSizesByProductProductId(productId);
            List<Object[]> sizes=productColorSizeRepository.findSizeCountsByProductId(productId);
            System.out.println("Items in Sizes:");
            for (Object[] sizeCount : sizes) {
                String size = (String) sizeCount[0];
                long itemCount = (long) sizeCount[1];


                System.out.println("Size: " + size + ", Available Items: " + itemCount);
            }

            List<String> colors=productColorSizeRepository.findColorsByProductProductId(productId);
            Map<String,byte[]>image_colors=new HashMap<>();
            byte[] image = new byte[0];
            for(ProductColorSize productColorSize:pcs) {
                byte[] imageData = productImageRepository.findByProductIdAndColor(productId, productColorSize.getColor());
                if (imageData != null) { 
                    image=imageData;
                    image_colors.put(productColorSize.getColor(), imageData);
                }

            }

            ProductInfoDTO productInfoDTO=new ProductInfoDTO();
            productInfoDTO.setProductId(productId);
            productInfoDTO.setProductName(product.getProductName());
            productInfoDTO.setPrice(product.getPrice());
            productInfoDTO.setColors(colors);
            productInfoDTO.setSizes(sizes);
            productInfoDTO.setImage_colors(image_colors);
            productInfoDTO.setImage(image);
            productInfoDTO.setCategory(product.getProductCategory());
            productInfoDTO.setDescription(product.getDescription());

            return  productInfoDTO;
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

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
    public List<ProductDTO> filterProducts(int minPrice, int maxPrice, List<Filter> selectedFilters) {
        Set<Product> filteredProducts = new HashSet<>();
        boolean firstFilter = true;

        // Get products filtered by categories, colors, sizes, etc.
        for (Filter filter : selectedFilters) {
            Set<Product> currentFilteredProducts=new HashSet<>();
            System.out.println("filter"+filter.getTitle());

             currentFilteredProducts = getProductsWithFilter(filter);
            System.out.println("filtered products"+currentFilteredProducts.size());
            if (firstFilter) {
                filteredProducts = currentFilteredProducts;
                firstFilter = false;
            } else {
                filteredProducts.retainAll(currentFilteredProducts);
            }
        }

        // Filter products by price range
        Set<Product> currentFilteredProducts = productRepository.findProductsbyPrice(minPrice, maxPrice);
        if (firstFilter) {
            filteredProducts = currentFilteredProducts;
        } else {
            filteredProducts.retainAll(currentFilteredProducts);
        }

        // Convert the set of filtered products to a list
        return mapToProductDTO(new ArrayList<>(filteredProducts));
    }

    public List<ProductDTO> getHomeProducts(Filter filter) {
        Set<Product> filteredProducts = new HashSet<>();
         filteredProducts = getProductsWithFilter(filter);
        return mapToProductDTO(new ArrayList<>(filteredProducts));


    }
    private List<ProductDTO> mapToProductDTO(List<Product> filteredProducts) {
        List<ProductDTO> filteredProductDTOs = new ArrayList<>();

        for (Product product : filteredProducts) {

            if (product != null) {

                List<ProductImage> productImages = productImageRepository.findByProductProductId(product.getProductId());
                if (productImages != null) { // Check if productImages is not null
                    filteredProductDTOs.add(new ProductDTO(product, productImages));
                } else {
                    // Handle the case when productImages is null
                    // You can log an error message or take appropriate action
                    System.out.println("Product images for product ID " + product.getProductId() + " are null.");
                }
            } else {
                // Handle the case when product is null
                // You can log an error message or take appropriate action
                System.out.println("Encountered a null product.");
            }
        }

        return filteredProductDTOs;
    }



    private Set<Product> getProductsWithFilter(Filter filter) {
        String title = filter.getTitle();
        String category = filter.getCategory();
        Set<Product> filteredProducts = null;

        if ("Category".equalsIgnoreCase(title)) {
            filteredProducts = filterByCategories(category);
        } else if ("Color".equalsIgnoreCase(title)) {
            filteredProducts = filterByColor(category);
        } else if ("Size".equalsIgnoreCase(title)) {
            filteredProducts = filterBySize(category);
        }else if ("Gender".equalsIgnoreCase(title)) {
            filteredProducts = filterByGender(category);
        }else if ("Brand".equalsIgnoreCase(title)) {
            filteredProducts = filterByBrand(category);
        }

        return filteredProducts;
    }

    private Set<Product> filterByCategories(String category) {
        return productRepository.findByProductCategory(category);
    }
    private Set<Product> filterByGender(String category) {
        return productRepository.findByGender(category);
    }
    private Set<Product> filterByBrand(String category) {
        Brand brand = brandRepository.findByBrandName(category);
        return productRepository.findByBrand(brand);
    }

    private Set<Product> filterByColor(String color) {
        return productColorSizeRepository.findProductsbyColor(color);
    }

    private Set<Product> filterBySize(String size) {
        return productColorSizeRepository.findProductsbySize(size);
    }
    public List<ProductDTO> filterProductsOld(FilterDTO filterDTO) {
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        if (filterDTO.getPrice() != null) {
            minPrice = filterDTO.getPrice().getMin();
            maxPrice = filterDTO.getPrice().getMax();
        }
        FilterDTO.PriceRange priceRange = filterDTO.getPrice();
        List<Product> filteredProducts = productColorSizeRepository.findFilteredProductColorSize(
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
        System.out.println(filteredProducts.size());
        for (Product product : filteredProducts) {

//            Product product = pcs.getProduct();
            List<ProductImage> productImages = productImageRepository.findByProductProductId(product.getProductId());
            filteredProductDTOs.add(new ProductDTO(product,productImages));

        }


        return filteredProductDTOs;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public void saveProduct(String productId, String productName, String price,String productCategory,String gender, String brandName, String description) throws IOException {
        try {
            // Check if product data is valid
            if (productId == null || productId.isEmpty() || productName == null || productName.isEmpty() || price == null || gender == null ||brandName == null|| productCategory==null || price.isEmpty() || description == null) {
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
            product.setDescription(description);
            productRepository.save(product);
            product.setDescription(description);
            Product p=productRepository.save(product);
            System.out.println("before saving description"+description);
            System.out.println("after saving description"+p.getDescription());



        } catch (InvalidProductDataException | ProductAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductSaveException("Failed to save product: " + e.getMessage());
        }
    }
}