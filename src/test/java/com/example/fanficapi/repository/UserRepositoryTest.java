package com.example.fanficapi.repository;


import com.example.fanficapi.TestPostgreSQLContainer;
import com.example.fanficapi.config.TestConfig;
import com.example.fanficapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfig.class)
@ContextConfiguration
@Testcontainers
@ActiveProfiles("test")
@Slf4j
class UserRepositoryTest {

    User testUser = new User("TestUser", "test@gmail.com", "testPassword", LocalDate.parse("1996-04-19"));
    User testUser2 = new User("TestUser2", "test_user@gmail.com", "testPassword2", LocalDate.parse("1989-05-20"));

    @Container
    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = TestPostgreSQLContainer.getInstance();

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.save(testUser);
        userRepository.save(testUser2);
    }

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    void containerIsRunning() {
        assertThat(POSTGRESQL_CONTAINER.isRunning(), is(true));
    }

    @Test
    void findByUsername() {
        User testUser = userRepository.findByUsername("TestUser").orElseThrow(() -> new RuntimeException("User not found"));
        User testUser2 = userRepository.findByUsername("TestUser2").orElseThrow(() -> new RuntimeException("User not found"));
        assertThat(testUser.getUsername(), is("TestUser"));
        assertThat(testUser2.getUsername(), is("TestUser2"));
    }

    @Test
    void existsByUsername() {
        assertThat(userRepository.existsByUsername("TestUser"), is(true));
        assertThat(userRepository.existsByUsername("TestUser2"), is(true));
        assertThat(userRepository.existsByUsername("JohnDoe"), is(false));
    }

    @Test
    void existsByEmail() {
        assertThat(userRepository.existsByEmail("test@gmail.com"), is(true));
        assertThat(userRepository.existsByEmail("prodUser@gmail.com"), is(false));
    }

    @Test
    void findByEmail() {
        User testUser = userRepository.findByEmail("test@gmail.com").orElseThrow(() -> new RuntimeException("User not found"));
        User testUser2 = userRepository.findByEmail("test_user@gmail.com").orElseThrow(() -> new RuntimeException("User not found"));
        assertThat(testUser.getEmail(), is("test@gmail.com"));
        assertThat(testUser, Matchers.hasProperty("username"));
        assertThat(testUser2.getEmail(), is("test_user@gmail.com"));
    }
}


