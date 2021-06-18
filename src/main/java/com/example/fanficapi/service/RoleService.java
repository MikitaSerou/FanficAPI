package com.example.fanficapi.service;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(RoleName roleName) throws RuntimeException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
