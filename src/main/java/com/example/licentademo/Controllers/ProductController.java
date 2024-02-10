package com.example.licentademo.Controllers;

import com.example.licentademo.Models.Product;
import com.example.licentademo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/product")
    public String postProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }
}
