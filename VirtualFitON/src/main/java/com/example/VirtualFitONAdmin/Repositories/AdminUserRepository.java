package com.example.VirtualFitONAdmin.Repositories;

import com.example.VirtualFitONAdmin.Models.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,String> {
    AdminUser findUserByUsername(String userName);

}
