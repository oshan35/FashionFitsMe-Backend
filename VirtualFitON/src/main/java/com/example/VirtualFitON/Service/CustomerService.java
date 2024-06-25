package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.*;
import com.example.VirtualFitON.Exceptions.MissingFieldException;
import com.example.VirtualFitON.Exceptions.UsernameAlreadyExistsException;
import com.example.VirtualFitON.Models.*;

import com.example.VirtualFitON.Repositories.CustomerMeasurementRepository;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import com.example.VirtualFitON.Repositories.ProductShoppingCartRepository;
import com.example.VirtualFitON.Repositories.ShoppingCartRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    private  PasswordEncoder passwordEncoder;


    private ProductShoppingCartRepository productShoppingCartRepository;


    private ShoppingCartRepository shoppingCartRepository;


    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomerMeasurementRepository customerMeasurementRepository;


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


    public Customer signUpCustomer(SignUpDTO signUpDTO) throws UsernameAlreadyExistsException {
        Customer existingCustomer = customerRepository.findByUsername(signUpDTO.getUsername());
        System.out.println("firstName"+signUpDTO.getFirstName());
        System.out.println("lastName"+signUpDTO.getLastName());
        System.out.println("username"+signUpDTO.getUsername());
        System.out.println("password"+signUpDTO.getPassword());
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
        ShoppingCart savedShoppingCart=shoppingCartRepository.save(shoppingCart);
        System.out.println("cart id after saving "+savedShoppingCart.getCartId());
        customer.setFirstName(signUpDTO.getFirstName());
        customer.setLastName(signUpDTO.getLastName());
        customer.setUsername(signUpDTO.getUsername());
        customer.setCart(savedShoppingCart);


        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        customer.setPassword(encodedPassword);

        Customer savedCustomer=customerRepository.save(customer);
        System.out.println("customer id id after signup "+savedCustomer.getCustomerId());
        return savedCustomer;

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
        List<ProductShoppingCart> cartProductList;
        cartProductList = productShoppingCartRepository.findCartProductsByCartId(carts_id);
        List<CartItemDTO> cartItems = shoppingCartService.getCartProductList(cartProductList);
        return cartItems;
    }
    public void saveCustomerBodyMeasurements(int customerId, Map<String, Object> bodyMeasurements, String bodymesh_URL) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerMeasurement customerMeasurement = new CustomerMeasurement();
        customerMeasurement.setCustomer(customer);

        // Set the id manually (example: generating a random number, UUID, or using a sequence)
        // In this example, let's use a simple incrementing id logic (this is just an example, you should use a more robust solution in production)
        Long newId = customerMeasurementRepository.count() + 1;
        customerMeasurement.setId(newId);

        if (bodyMeasurements.containsKey("ankle_circumference")) {
            customerMeasurement.setAnkleCircumference((Double) bodyMeasurements.get("ankle_circumference"));
        }
        if (bodyMeasurements.containsKey("arm_length")) {
            customerMeasurement.setArmLength((Double) bodyMeasurements.get("arm_length"));
        }
        if (bodyMeasurements.containsKey("bicep_circumference")) {
            customerMeasurement.setBicepCircumference((Double) bodyMeasurements.get("bicep_circumference"));
        }
        if (bodyMeasurements.containsKey("calf_circumference")) {
            customerMeasurement.setCalfCircumference((Double) bodyMeasurements.get("calf_circumference"));
        }
        if (bodyMeasurements.containsKey("chest_circumference")) {
            customerMeasurement.setChestCircumference((Double) bodyMeasurements.get("chest_circumference"));
        }
        if (bodyMeasurements.containsKey("forearm_circumference")) {
            customerMeasurement.setForearmCircumference((Double) bodyMeasurements.get("forearm_circumference"));
        }
        if (bodyMeasurements.containsKey("head_circumference")) {
            customerMeasurement.setHeadCircumference((Double) bodyMeasurements.get("head_circumference"));
        }
        if (bodyMeasurements.containsKey("hip_circumference")) {
            customerMeasurement.setHipCircumference((Double) bodyMeasurements.get("hip_circumference"));
        }
        if (bodyMeasurements.containsKey("inside_leg_length")) {
            customerMeasurement.setInsideLegLength((Double) bodyMeasurements.get("inside_leg_length"));
        }
        if (bodyMeasurements.containsKey("neck_circumference")) {
            customerMeasurement.setNeckCircumference((Double) bodyMeasurements.get("neck_circumference"));
        }
        if (bodyMeasurements.containsKey("shoulder_breadth")) {
            customerMeasurement.setShoulderBreadth((Double) bodyMeasurements.get("shoulder_breadth"));
        }
        if (bodyMeasurements.containsKey("shoulder_to_crotch")) {
            customerMeasurement.setShoulderToCrotch((Double) bodyMeasurements.get("shoulder_to_crotch"));
        }
        if (bodyMeasurements.containsKey("thigh_circumference")) {
            customerMeasurement.setThighCircumference((Double) bodyMeasurements.get("thigh_circumference"));
        }
        if (bodyMeasurements.containsKey("waist_circumference")) {
            customerMeasurement.setWaistCircumference((Double) bodyMeasurements.get("waist_circumference"));
        }
        if (bodyMeasurements.containsKey("wrist_circumference")) {
            customerMeasurement.setWristCircumference((Double) bodyMeasurements.get("wrist_circumference"));
        }

        if (bodymesh_URL != null){
            customerMeasurement.setBodyModelUrl(bodymesh_URL);
        }

        customerMeasurementRepository.save(customerMeasurement);
    }


}
