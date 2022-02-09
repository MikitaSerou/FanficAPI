package com.example.fanficapi.mapper;

import com.example.fanficapi.dto.user.UserDto;
import com.example.fanficapi.dto.user.UserShortInfoDto;
import com.example.fanficapi.model.User;
import com.example.fanficapi.payload.SignUpRequest;
import com.example.fanficapi.service.RoleService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder encoder;

    @Autowired
    protected RoleService roleService;

    public abstract UserShortInfoDto userToShortInfoDto(User user);

    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "password", expression = "java(encoder.encode(signUpRequest.getPassword()))")
    @Mapping(target = "roles", expression = "java(java.util.Set.of(roleService.findByRoleName(RoleName.ROLE_USER)))")
    public abstract User userFromSignUpRequest(SignUpRequest signUpRequest);

    @Named(value = "userToDto")
    public abstract UserDto userToDto(User user);

    public abstract User userDtoToUser(UserDto user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void mergeUsers(User dto, @MappingTarget User entity);
}
