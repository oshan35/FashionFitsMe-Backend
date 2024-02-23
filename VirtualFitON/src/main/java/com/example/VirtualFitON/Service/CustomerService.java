package com.example.VirtualFitON.Service;
import com.example.VirtualFitON.DTO.CustomerRegisterDTO;
import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Exceptions.UsernameAlreadyExistsException;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.VirtualFitON.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private  PasswordEncoder passwordEncoder;

    public boolean authenticateCustomer(LoginRequestDto loginRequestDto) {
        Customer customer= customerRepository.findByUsername(loginRequestDto.getUsername());

        if (customer != null && passwordEncoder.matches(loginRequestDto.getPassword(), customer.getPassword())) {
            return true;
        } else {
            return false;
        }

    }


    public void registerCustomer(CustomerRegisterDTO customerDTO) throws UsernameAlreadyExistsException {
        Customer existingCustomer = customerRepository.findByUsername(customerDTO.getUsername());
        if (existingCustomer != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }


        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setCountry(customerDTO.getCountry());
        customer.setUsername(customerDTO.getUsername());
        customer.setCustomerId(customerDTO.getCustomerId());

        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
        customer.setPassword(encodedPassword);

        customerRepository.save(customer);
    }

}
