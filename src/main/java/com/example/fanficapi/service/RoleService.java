package com.example.fanficapi.service;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;

public interface RoleService {

    Role findByRoleName(RoleName roleName) throws RuntimeException;
}
