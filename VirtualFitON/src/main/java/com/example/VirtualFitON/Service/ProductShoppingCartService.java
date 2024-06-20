package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.ProductDTO;
import com.example.VirtualFitON.Exceptions.CustomerNotFoundException;
import com.example.VirtualFitON.Exceptions.DatabaseAccessException;
import com.example.VirtualFitON.Exceptions.InvalidProductDataException;
import com.example.VirtualFitON.Exceptions.ProductAlreadyExistsException;
import com.example.VirtualFitON.Models.*;
import com.example.VirtualFitON.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductShoppingCartService{
    private final ProductShoppingCartRepository productShoppingCartRepository;


    private final ShoppingCartRepository shoppingCartRepository;


    private final ProductImageRepository productImageRepository;


    private final ProductRepository productRepository;


    private final CustomerRepository customerRepository;

    @Autowired
    public ProductShoppingCartService(ProductShoppingCartRepository productShoppingCartRepository, ShoppingCartRepository shoppingCartRepository, ProductColorSizeRepository productColorSizeRepository, ProductImageRepository productImageRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productShoppingCartRepository = productShoppingCartRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public List<ProductDTO> getProductListByCartId(int cartId) {
        List<Product> products = productShoppingCartRepository.findProductsByCartId(cartId);

        return mapToProductDTO(products);

    }

    public List<Product> getCartProductListByCartId(int cartId) {
        List<Product> products = productShoppingCartRepository.findProductsByCartId(cartId);

        return products;

    }
    public void addProductToCart(String productId, int customerId, String selectedColor, String selectedSize) throws InvalidProductDataException, DatabaseAccessException, ProductAlreadyExistsException {

        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            ShoppingCart shoppingCart=customer.getCart();
            int cartId=shoppingCart.getCartId();


            Product product = productRepository.findByProductId(productId);

            ProductShoppingCart productShoppingCart = new ProductShoppingCart();

            productShoppingCart.setId(new ProductShoppingCartId(productId, cartId));

            productShoppingCart.setProduct(product);

            productShoppingCart.setCart(shoppingCart);

            productShoppingCart.setOrderQuantity(1);

            productShoppingCart.setProductColor(selectedColor);

            productShoppingCart.setProductSize(selectedSize);


            productShoppingCartRepository.save(productShoppingCart);

        } catch (DuplicateKeyException e) {
            throw new ProductAlreadyExistsException("Product already exists in the cart");
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Database access error occurred while adding product to cart"+ e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while adding product to cart", e);
        }
    }
    private List<ProductDTO> mapToProductDTO(List<Product> filteredProducts) {
        List<ProductDTO> filteredProductDTOs = new ArrayList<>();

        for (Product product : filteredProducts) {

            if (product != null) {

                List<ProductImage> productImages = productImageRepository.findByProductProductId(product.getProductId());
                if (productImages != null) { // Check if productImages is not null
                    filteredProductDTOs.add(new ProductDTO(product, productImages));
                } else {

                    System.out.println("Product images for product ID " + product.getProductId() + " are null.");
                }
            } else {

                System.out.println("Encountered a null product.");
            }
        }
        return filteredProductDTOs;
    }

    public BigDecimal getTotalAmount(int cartId) {

        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findByCartId(cartId);
            if (shoppingCart != null) {
                return shoppingCart.getTotalAmount();
            } else {
                throw new EmptyResultDataAccessException("Shopping cart not found with ID: " + cartId, 1);
            }
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public BigDecimal getDiscountAmount(int cartId) {
        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findByCartId(cartId);
            if (shoppingCart != null) {
                return shoppingCart.getDiscountAmount();
            } else {
                throw new EmptyResultDataAccessException("Shopping cart not found with ID: " + cartId, 1);
            }
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public List<ProductColorSize> getProductColorSizeByCartId(int cartId) {
        return productShoppingCartRepository.findProductColorSizeByCartId(cartId);
    }


    public void removeProductFromCart(String productId, int customerId) throws Exception {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId);
            ShoppingCart shoppingCart = customer.getCart();
            int cartId = shoppingCart.getCartId();

            ProductShoppingCartId productShoppingCartId = new ProductShoppingCartId(productId, cartId);
            productShoppingCartRepository.deleteById(productShoppingCartId);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Database access error occurred while removing product from cart"+ e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while removing product from cart", e);
        }
    }



}