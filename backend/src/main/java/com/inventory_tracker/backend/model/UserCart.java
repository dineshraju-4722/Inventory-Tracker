package com.inventory_tracker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCartId;

    private Long quantity;

    @ManyToMany(mappedBy = "userCart")
    private List<Product> productName;

    @OneToOne(mappedBy = "userCart" )
    private User user;



}
