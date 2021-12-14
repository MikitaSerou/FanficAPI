package com.example.fanficapi.service;

import com.example.fanficapi.dto.RoleDto;
import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService extends AbstractService<Role, Short, RoleDto, RoleDto> {

    private final RoleRepository roleRepository;


    public Role findByRoleName(RoleName roleName) throws RuntimeException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    @Override
    public void saveToDB(Role object) {

    }

    @Override
    public Role update(Role object) {
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
    public List<RoleDto> getAllDto() {
        return null;
    }

    @Override
    public RoleDto getSimpleDtoById(Short id) {
        return null;
    }

    @Override
    public RoleDto getDtoById(Short id) {
        return null;
    }

    @Override
    public Role findByUsername(String name) {
        return null;
    }
}
