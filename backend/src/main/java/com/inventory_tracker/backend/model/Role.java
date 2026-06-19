package com.inventory_tracker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AppRole roleName;

    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST})
    private List<User> users;

    public Role(AppRole roleName) {
        this.roleName = roleName;
    }
}
