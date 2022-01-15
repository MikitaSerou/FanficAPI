package com.example.fanficapi.service.impl;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.repository.RoleRepository;
import com.example.fanficapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName) throws RuntimeException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

}
