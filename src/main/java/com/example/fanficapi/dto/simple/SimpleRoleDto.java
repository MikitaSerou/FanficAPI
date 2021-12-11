package com.example.fanficapi.dto.simple;

import com.example.fanficapi.enums.RoleName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleRoleDto {

    Short id;
    RoleName name;
}