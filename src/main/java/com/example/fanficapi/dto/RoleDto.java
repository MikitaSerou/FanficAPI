package com.example.fanficapi.dto;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Short id;

    private RoleName name;

    private Set<User> users;
}
