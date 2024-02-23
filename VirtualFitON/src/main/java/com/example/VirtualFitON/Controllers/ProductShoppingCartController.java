package com.example.VirtualFitON.Controllers;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Service.ProductShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductShoppingCartController {
    @Autowired
    ProductShoppingCartService productShoppingCartService;
    @Autowired
    ProductShoppingCartRepository productShoppingCartRepository;

    @GetMapping("/product_shopping_cart/{cartId}")
    public ResponseEntity<?> getProductListByCartId(@PathVariable String cartId) {
        try {
            List<Product> productList = productShoppingCartService.getProductListByCartId(cartId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/products/{Id}")
    public ResponseEntity<?> getProduct(@PathVariable String Id) {
        try {
            System.out.println(Id);
            Product p = productShoppingCartRepository.findProductsByProduct_Id(Id);
            return new ResponseEntity<>(p.getPrice(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
