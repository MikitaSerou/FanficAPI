package com.example.fanficapi.dto.role;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto extends ParentRoleDto{

    Set<User> users;

    public RoleDto(Short id, RoleName name, Set<User> users) {
        super(id, name);
        this.users = users;
    }
}
