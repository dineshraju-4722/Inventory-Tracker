package com.inventory_tracker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String email;

    private String password;

//    @OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST , CascadeType.REMOVE })
//    private UserCart userCart;

}
