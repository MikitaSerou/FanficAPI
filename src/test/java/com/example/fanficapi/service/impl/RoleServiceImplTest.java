package com.example.fanficapi.service.impl;

import com.example.fanficapi.config.TestConfig;
import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.exception.RoleException;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = TestConfig.class)
class RoleServiceImplTest {

    private final Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
    private final Role roleUser = new Role(RoleName.ROLE_USER);
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleServiceImpl roleService;

    @DisplayName("Find role from Roles enumeration")
    @Test
    void findByRoleName() {
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(roleUser));
        when(roleRepository.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(roleAdmin));
        Role roleUsr = roleService.findByRoleName(RoleName.ROLE_USER);
        Role roleAdm = roleService.findByRoleName(RoleName.ROLE_ADMIN);

        assertThat(roleUsr, hasProperty("name", is(RoleName.ROLE_USER)));
        assertThat(roleUsr, equalToObject(roleUser));
        assertThat(roleAdm, hasProperty("name", is(RoleName.ROLE_ADMIN)));
        assertThat(roleAdm, equalToObject(roleAdmin));

        verify(roleRepository, times(2)).findByName(any());
    }

    @DisplayName("Try to find role from enumeration if this role not in database there")
    @Test
    void findNotExistenceRoleByRoleName() {
        when(roleRepository.findByName(any())).thenReturn(Optional.empty()); //mock situation in case if role not in database
        assertThrows(RoleException.class, () -> roleService.findByRoleName(RoleName.ROLE_ADMIN));
    }

}
