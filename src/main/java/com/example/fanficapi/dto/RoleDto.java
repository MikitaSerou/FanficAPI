package com.example.fanficapi.dto;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Short id;
    RoleName name;
    Set<User> users;
}
