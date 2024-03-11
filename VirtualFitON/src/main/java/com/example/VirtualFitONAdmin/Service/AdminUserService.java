package com.example.VirtualFitONAdmin.Service;

import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitONAdmin.DTO.AdminLoginRequestDTO;
import com.example.VirtualFitONAdmin.Models.AdminUser;
import com.example.VirtualFitONAdmin.Repositories.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    @Autowired
    private AdminUser adminUser;
    private AdminUserRepository adminUserRepository;
    private PasswordEncoder passwordEncoder;

    public boolean authenticateCustomer(AdminLoginRequestDTO adminLoginRequestDTO) {
        Customer customer= adminUserRepository.findUserByUsername();

        if (customer != null && passwordEncoder.matches(adminLoginRequestDTO.getUsername(), adminLoginRequestDTO.getPassword())) {
            return true;
        } else {
            return false;
        }

    }



}
