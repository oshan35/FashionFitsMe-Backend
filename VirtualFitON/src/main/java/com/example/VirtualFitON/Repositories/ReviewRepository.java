package com.example.VirtualFitON.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,String> {

    List<Review> findByProductProductId(String productId);
}
