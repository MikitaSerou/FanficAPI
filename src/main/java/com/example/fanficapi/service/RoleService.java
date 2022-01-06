package com.example.fanficapi.service;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;

import java.util.List;

public interface RoleService {

    Role findByRoleName(RoleName roleName) throws RuntimeException;

    void saveToDB(Role role);

    Role update(Role role);

    void deleteById(Short id);

    List<Role> findAll();

    Role findById(Short id);

    Role findByUsername(String name);
}
