package com.example.fanficapi.service.impl;

import com.example.fanficapi.enums.RoleName;
import com.example.fanficapi.model.Role;
import com.example.fanficapi.model.User;
import com.example.fanficapi.payload.UserDetailsImpl;
import com.example.fanficapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private final User johnDoe = User
            .builder()
            .username("JohnDoe")
            .email("JohnDoe@gmail.com")
            .password("123456Y!")
            .birthDate(LocalDate.now())
            .roles(Set.of(new Role(RoleName.ROLE_USER))).build();

    private final User marieSue = User
            .builder()
            .username("MarieSue")
            .email("MarieSue@gmail.com")
            .password("1234589H!")
            .birthDate(LocalDate.now())
            .roles(Set.of(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_ADMIN))).build();


    @DisplayName("Should return user details")
    @Test
    void loadUserByUsernameTest() {
        when(userService.findByUsername("JohnDoe")).thenReturn(johnDoe);
        when(userService.findByUsername("MarieSue")).thenReturn(marieSue);

        UserDetailsImpl userDetailsJohnDoe = UserDetailsImpl.build(johnDoe);
        UserDetailsImpl userDetailsMarieSue = UserDetailsImpl.build(marieSue);
        log.info("UserDetailsJohnDoe: {}", userDetailsJohnDoe);
        log.info("UserDetailsMarieSue: {}", userDetailsMarieSue);

        assertEquals(userDetailsJohnDoe, userDetailsService.loadUserByUsername("JohnDoe"));
        assertThat(userDetailsJohnDoe, hasProperty("authorities", hasSize(1)));
        assertThat(userDetailsJohnDoe, hasProperty("authorities", hasItem(hasProperty("authority", is("ROLE_USER")))));
        assertThat(userDetailsJohnDoe, hasProperty("username", is("JohnDoe")));
        assertThat(userDetailsJohnDoe, hasProperty("password", is("123456Y!")));

        assertEquals(userDetailsMarieSue, userDetailsService.loadUserByUsername("MarieSue"));
        assertThat(userDetailsMarieSue, hasProperty("authorities", hasSize(2)));
        assertThat(userDetailsMarieSue, hasProperty("authorities", hasItem(hasProperty("authority", is("ROLE_USER")))));
        assertThat(userDetailsMarieSue, hasProperty("authorities", hasItem(hasProperty("authority", is("ROLE_ADMIN")))));
        assertThat(userDetailsMarieSue, hasProperty("username", is("MarieSue")));
        assertThat(userDetailsMarieSue, hasProperty("password", is("1234589H!")));

        verify(userService).findByUsername("JohnDoe");
        verify(userService).findByUsername("MarieSue");
    }

    @DisplayName("Shouldn't return user details")
    @Test
    void failedLoadUserByUsernameTest() {
        when(userService.findByUsername("JohnDoe")).thenThrow(UsernameNotFoundException.class);
        UserDetailsImpl.build(johnDoe);
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("JohnDoe"));
    }
}
