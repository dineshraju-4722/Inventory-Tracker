package com.inventory_tracker.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn( name = "role_id" )
    )
    private Set<Role> roles = new HashSet<>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST})
    private List<ImportedHistory> importedHistories;
//    @OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST , CascadeType.REMOVE })
//    private UserCart userCart;

}
