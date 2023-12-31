package com.example.managementtask.store.repositories;

import com.example.managementtask.store.enums.ERole;
import com.example.managementtask.store.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);

    Boolean existsByName(ERole role);
}