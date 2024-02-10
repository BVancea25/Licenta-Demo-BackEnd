package com.example.licentademo.Service;

import com.example.licentademo.Models.Product;
import com.example.licentademo.Models.User;
import com.example.licentademo.Models.Wishlist;
import com.example.licentademo.Repositories.ProductRepo;
import com.example.licentademo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class WishlistService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    public String addToWishlist(Integer productId){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            User user= (User) userRepo.findByEmail(username).orElse(null);

            assert user != null;
            Wishlist wishlist=user.getWishlist();

            Product product=productRepo.findById(productId).orElse(null);
            if (product != null) {
                wishlist.getProducts().add(product);
            } else {
                return  "Product not found with id: " + productId;
            }

            user.setWishlist(wishlist);
            userRepo.save(user);

            return "Item added to wishlist";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
