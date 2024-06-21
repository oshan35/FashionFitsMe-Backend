package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.MissingFieldException;
import com.example.VirtualFitON.Exceptions.UsernameAlreadyExistsException;
import com.example.VirtualFitON.Models.Product;
import com.example.VirtualFitON.Models.ProductShoppingCart;

import com.example.VirtualFitON.Models.ShoppingCart;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.VirtualFitON.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    private  PasswordEncoder passwordEncoder;


    private ProductShoppingCartRepository productShoppingCartRepository;


    private ShoppingCartRepository shoppingCartRepository;


    private ShoppingCartService shoppingCartService;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, ProductShoppingCartRepository productShoppingCartRepository, ShoppingCartRepository shoppingCartRepository, ShoppingCartService shoppingCartService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.productShoppingCartRepository = productShoppingCartRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public CustomerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticateCustomer(LoginRequestDto loginRequestDto) {
        Customer customer= customerRepository.findByUsername(loginRequestDto.getUsername());

        if (customer != null && passwordEncoder.matches(loginRequestDto.getPassword(), customer.getPassword())) {
            return true;
        } else {
            return false;
        }

    }


//    public void registerCustomer(CustomerRegisterDTO customerDTO) throws UsernameAlreadyExistsException {
//        Customer existingCustomer = customerRepository.findByUsername(customerDTO.getUsername());
//        if (existingCustomer != null) {
//            throw new UsernameAlreadyExistsException("Username already exists");
//        }
//        if (customerDTO.getFirstName() == null || customerDTO.getLastName() == null ||
//                customerDTO.getCountry() == null || customerDTO.getUsername() == null ||
//                customerDTO.getPassword() == null ) {
//            throw new MissingFieldException("One or more required fields are missing");
//        }
//
//
//        Customer customer = new Customer();
//
//        customer.setFirstName(customerDTO.getFirstName());
//        customer.setLastName(customerDTO.getLastName());
//        customer.setCountry(customerDTO.getCountry());
//        customer.setUsername(customerDTO.getUsername());
//
//
//        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
//        customer.setPassword(encodedPassword);
//
//        customerRepository.save(customer);
//    }


    public void signUpCustomer(SignUpDTO signUpDTO) throws UsernameAlreadyExistsException {
        Customer existingCustomer = customerRepository.findByUsername(signUpDTO.getUsername());
        if (existingCustomer != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (    signUpDTO.getFirstName() == null ||
                signUpDTO.getLastName() == null  ||
                signUpDTO.getUsername() == null ||
                signUpDTO.getPassword() == null ) {
            throw new MissingFieldException("One or more required fields are missing");
        }


        Customer customer = new Customer();
        ShoppingCart shoppingCart=new ShoppingCart();
        System.out.println("cart id"+shoppingCart.getCartId());
        customer.setFirstName(signUpDTO.getFirstName());
        customer.setLastName(signUpDTO.getLastName());
        customer.setUsername(signUpDTO.getUsername());
        customer.setCart(shoppingCart);


        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        customer.setPassword(encodedPassword);

        customerRepository.save(customer);
    }

    public Customer LoginCustomer(LoginDTO loginDTO)  {
        Customer customer = customerRepository.findByUsername(loginDTO.getUsername());
        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + loginDTO.getUsername());
        }
        return customer;
    }

    public List<CartItemDTO> getCustomerCartItems(int customerId){
        int carts_id = customerRepository.findCartId(customerId);
        System.out.println("Test 01");
        List<ProductShoppingCart> cartProductList;
        cartProductList = productShoppingCartRepository.findCartProductsByCartId(carts_id);
        System.out.println("Test 02");
        List<CartItemDTO> cartItems = shoppingCartService.getCartProductList(cartProductList);
        System.out.println("Test 03");
        System.out.println("no of cart items" + cartItems.size());
        return cartItems;
    }




}
