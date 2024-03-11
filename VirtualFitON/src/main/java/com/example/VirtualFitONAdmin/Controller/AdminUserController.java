package com.example.VirtualFitONAdmin.Controller;

import com.example.VirtualFitON.DTO.LoginRequestDto;
import com.example.VirtualFitONAdmin.Service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ) {

        if (adminUserService.authenticateCustomer(loginRequest)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


}
