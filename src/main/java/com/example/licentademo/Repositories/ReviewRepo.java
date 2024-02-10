package com.example.licentademo.Repositories;

import com.example.licentademo.Models.Product;
import com.example.licentademo.Models.Review;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findAllByProduct(Product product);
}
