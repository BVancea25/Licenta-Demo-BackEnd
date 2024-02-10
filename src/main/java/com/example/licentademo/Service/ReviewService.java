package com.example.licentademo.Service;

import com.example.licentademo.DTO.ProductReviewsDTO;
import com.example.licentademo.Models.Product;
import com.example.licentademo.Models.Review;
import com.example.licentademo.Models.User;
import com.example.licentademo.Repositories.ProductRepo;
import com.example.licentademo.Repositories.ReviewRepo;
import com.example.licentademo.Repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public String addReview(Review review, Integer productId, Integer userId){
        try {


            User user=userRepo.findById(userId).orElse(null);
            assert user!=null;
            review.setUser(user);

            Product product=productRepo.findById(productId).orElse(null);
            review.setProduct(product);

            reviewRepo.save(review);
            return "Review added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public List<ProductReviewsDTO> getReviews(Integer productId) {

        Product product=productRepo.findById(productId).orElse(null);
        List<Review> reviews= reviewRepo.findAllByProduct(product);

        List<ProductReviewsDTO> reviewsDTOS=new ArrayList<>();

        for (Review review:
             reviews) {
            ProductReviewsDTO dto=ProductReviewsDTO
                    .builder()
                    .reviewId(review.getReviewId())
                    .rating(review.getRating())
                    .userName(review.getUser().getName())
                    .comment(review.getComment())
                    .build();
            reviewsDTOS.add(dto);
        }

        return reviewsDTOS;
    }
}
