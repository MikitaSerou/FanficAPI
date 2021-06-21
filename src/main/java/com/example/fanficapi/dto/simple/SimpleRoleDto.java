package com.example.fanficapi.dto.simple;

import com.example.fanficapi.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleRoleDto {

    private Short id;

    private RoleName name;
}