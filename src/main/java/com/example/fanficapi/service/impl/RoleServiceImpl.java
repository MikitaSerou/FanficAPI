package com.example.fanficapi.service.impl;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.repository.RoleRepository;
import com.example.fanficapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName) throws RuntimeException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    @Override
    public void saveToDB(Role role) {

    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void deleteById(Short id) {

    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(Short id) {
        return null;
    }

    @Override
    public Role findByUsername(String name) {
        return null;
    }
}
