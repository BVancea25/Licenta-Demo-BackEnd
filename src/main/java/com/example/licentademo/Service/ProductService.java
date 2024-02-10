package com.example.licentademo.Service;

import com.example.licentademo.Models.Product;
import com.example.licentademo.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public String addProduct(Product product){
        try {
            productRepo.save(product);
            return "Product added";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    public List<Product> getProducts() {
        return productRepo.findAll();

    }

    public Product getProduct(Integer id) {
        return productRepo.findById(id).orElse(null);
    }
}
