package com.example.licentademo.DTO;

import com.example.licentademo.Models.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductReviewsDTO {
    private String comment;
    private Integer rating;
    private Integer reviewId;
    private String userName;


}
