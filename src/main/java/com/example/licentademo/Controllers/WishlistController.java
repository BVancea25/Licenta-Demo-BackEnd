package com.example.licentademo.Controllers;

import com.example.licentademo.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/wishlist/{product_id}")
    public String postToWishlist(@PathVariable Integer product_id){
        return wishlistService.addToWishlist(product_id);
    }
}
