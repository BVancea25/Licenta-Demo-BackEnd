package com.example.licentademo.Controllers;

import com.example.licentademo.DTO.ProductReviewsDTO;
import com.example.licentademo.Models.Review;
import com.example.licentademo.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/review/{product_id}/{user_id}")
    public String postReview(@RequestBody Review review, @PathVariable(name = "product_id") Integer productId,@PathVariable(name = "user_id") Integer userId){
        return reviewService.addReview(review,productId,userId);
    }

    @GetMapping("/review/{product_id}")
    public List<ProductReviewsDTO> getReviews(@PathVariable Integer product_id){
        return reviewService.getReviews(product_id);
    }
}
