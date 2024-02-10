package com.example.licentademo.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue
    private Integer wishlistId;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "wishlist_products",
            joinColumns=@JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
