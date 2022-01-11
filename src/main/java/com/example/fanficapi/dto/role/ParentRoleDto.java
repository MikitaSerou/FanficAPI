package com.example.fanficapi.dto.role;

import com.example.fanficapi.enums.RoleName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ParentRoleDto {

    Short id;
    RoleName name;
}
