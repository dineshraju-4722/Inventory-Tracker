package com.inventory_tracker.backend.repositories;


import com.inventory_tracker.backend.model.AppRole;
import com.inventory_tracker.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role  ,Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
