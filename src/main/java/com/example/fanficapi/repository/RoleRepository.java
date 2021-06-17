package com.example.fanficapi.repository;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByName(RoleName name);
}

