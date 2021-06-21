package com.example.fanficapi.dto;

import com.example.fanficapi.enums.RoleName;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Short id;

    private RoleName name; //TODO it's enum!
}
