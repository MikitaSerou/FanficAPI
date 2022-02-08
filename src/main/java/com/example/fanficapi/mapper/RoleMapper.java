package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.role.ParentRoleDto;
import com.example.fanficapi.model.Role;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleMapper {
    public abstract ParentRoleDto roleToSimpleDto(Role role);

}
